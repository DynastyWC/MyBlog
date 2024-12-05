<script setup lang="ts">
import robot from '../component/robot.vue'
import {ref} from "vue";
// const playAudio = () =>{
//   fetch('', {
//     method: 'GET',
//   })
//     .then((response) => {
//       console.log('HTTP响应:', response);
//       // 返回 response.blob(); 这里应根据需求处理响应数据
//     })
//     .then((mp3Blob) => {
//       const audioURL = URL.createObjectURL(mp3Blob);
//       const audioElement = new Audio(audioURL);
//       audioElement.play();
//     })
//     .catch((error) => {
//       console.error('发生错误：', error);
//     });
// }
const isAction = ref<boolean>(false);
const count = ref<number>(0); // 计算点击次数
let timer: NodeJS.Timeout | null = null; // 计时
const nowTime = ref<string>('00:00:00'); // 时间显示
let hour = ref<number>(0);
let minutes = ref<number>(0);
let seconds = ref<number>(0);
let errorMessage = '';
let successMessage = '';
const maxChancesPerDifficulty = 2; // 最大机会次数
const difficulties = [
  { name: '难度1', audio: '/public/video/video2.mp3' },
  { name: '难度2', audio: '/public/video/video3.mp3' },
  { name: '难度3', audio: '/public/video/video4.mp3' }
];
let currentDifficulty = 0;
let remainingChances = maxChancesPerDifficulty; // 初始机会次数
const actionGame = () => {
  nowTime.value = '00:00:00';
  hour = ref(0);
  minutes = ref(0);
  seconds = ref(0);
  if (timer) {
    clearInterval(timer);
  }
  timer = setInterval(startTimer, 1000);
  isAction.value = true;
};
const startTimer = () => {
  seconds.value += 1;
  if (seconds.value >= 60) {
    seconds.value = 0;
    minutes.value += 1;
  }
  if (minutes.value >= 60) {
    minutes.value = 0;
    hour.value += 1;
  }
  nowTime.value = `${toZero(hour.value) + ':' + toZero(minutes.value) + ':' + toZero(seconds.value)}`;
};
const toZero = (timeNumber: number): string => {
  return timeNumber < 10 ? '0' + timeNumber : timeNumber.toString();
};
const playAudio =() =>{
  const audioElement = new Audio(difficulties[currentDifficulty].audio);
  audioElement.play().then(() => {
    //音频开始播放
  }).catch(error => {
    console.error('发生错误',error)
  })
}
const successDialog = ref(false);
const errorDialog = ref(false);

const showSuccessDialog = () => {
  successDialog.value = true;
  if (difficulties[currentDifficulty].name != '难度3'){
    successMessage = `恭喜你，${ difficulties[currentDifficulty].name }回答正确！`
}else {
    successMessage = '恭喜你，你已经完成所有难度，真棒！请继续下一项测试'
  }

}

const showErrorDialog = () => {
  errorDialog.value = true;
  // 减少机会次数
  remainingChances--;

  if (remainingChances === 0) {
    errorMessage = '测试结束，请继续下一项测试';
  } else {
    errorMessage = `很抱歉，回答错误，你还有${remainingChances}次机会。`;
  }
};

const closeDialog = () => {
  successDialog.value = false;
  errorDialog.value = false;
};
const moveToNextDifficulty = async () => {
  if (currentDifficulty < difficulties.length - 1) {
    currentDifficulty++;

    // 重置机会次数
    remainingChances = maxChancesPerDifficulty;
  } else {
    console.log("测试完成")
  }
}
const handleSuccess = async () => {
  await showSuccessDialog();
  moveToNextDifficulty();
}

</script>

<template>
  <v-card
    variant="flat"
    height="100vh"
    class=" justify-center align-center text-center"
  >
    <v-main class="main">
      <v-container>
        <v-row v-if="isAction">
          <v-col cols="12">
            <v-card class="timer">
              <v-card-text>{{ nowTime }}</v-card-text>
            </v-card>
          </v-col>
          <v-col cols="12">
          请听下面一段话
          <robot></robot>
          <v-btn @click="playAudio">播放音频</v-btn>
          <p>当前难度: {{ difficulties[currentDifficulty].name }}</p>
          <!-- 添加两新按钮，使用v-row和v-col进行布局 -->
          <v-row justify="center" class="mt-5">
            <v-col cols="auto">
              <v-btn
                color="red"
                fab
                dark
                class="ma-2"
                @click="showErrorDialog"
              >
                <v-icon>mdi-close</v-icon>
              </v-btn>
            </v-col>
            <v-col cols="auto">
              <v-btn
                color="green"
                fab
                dark
                class="ma-2"
                @click="handleSuccess"
              >
                <v-icon>mdi-check</v-icon>
              </v-btn>
            </v-col>
          </v-row>
          </v-col>
        </v-row>
        <v-row v-else>
          <v-col cols="6" class="text-center">
            <v-card
              variant="flat"
              height="100vh"
              class=" justify-center align-center text-center"
            >
              <robot></robot>
            </v-card>
          </v-col>
          <v-col cols="6" >
            <v-col cols="3" class="text-center">
              <v-btn @click="actionGame" color="primary" class="action-button-class">开始测试</v-btn>
            </v-col>
            <v-col cols="2">
              <v-card
                class="mx-auto"
                width="400"
                prepend-icon="mdi-home"
              >
                <template v-slot:title>
                  听力测试
                </template>

                <v-card-text>
                  该测试分为三个难度，当点击开始的时候，将会播放一段音频，请尽量将音频复述出来吧！
                </v-card-text>
              </v-card>
            </v-col>
          </v-col>
        </v-row>
      </v-container>
    </v-main>


    <!-- 成功提示框 -->
    <v-dialog v-model="successDialog" max-width="300">
      <v-alert type="success">
        <v-card-title class="headline">
          回答正确
        </v-card-title>
        <v-card-text>
          {{successMessage}}
        </v-card-text>
        <v-card-actions>
          <v-btn  @click="closeDialog">确定</v-btn>
        </v-card-actions>
      </v-alert>
    </v-dialog>

    <!-- 错误提示框 -->
    <v-dialog v-model="errorDialog" max-width="300">
      <v-alert type="error">
        <v-card-title class="headline">
          回答错误
        </v-card-title>
        <v-card-text>
          {{ errorMessage }}
        </v-card-text>
        <v-card-actions>
          <v-btn  @click="closeDialog">确定</v-btn>
        </v-card-actions>
      </v-alert>
    </v-dialog>
  </v-card>
</template>

<style scoped lang="scss">
.action-button-class {
  margin-top: 2vh;
  width: 15vh;
  height: 5vh;
  font-size: 2vh;
}
</style>
