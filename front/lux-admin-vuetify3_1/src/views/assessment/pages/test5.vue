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
          <div class="div-class" >
            <v-card v-for="(item, index) of number"
                    :key="index"
                    class="box-class"
                    @click="handleButtonClick(index)"
                    :color="item.color"
                    :disabled="isButtonsDisabled"
            >
              <span class="span-class">{{ item.number }}</span>
            </v-card>
            <v-btn style="margin-top: 10px;" @click="startNextRound">开始</v-btn>
          </div>
          <!-- 开始按钮 -->

          <robot></robot>
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
                  记忆力测试
                </template>

                <v-card-text>
                  该测试分为三个难度，当点击开始的时候，方格中的颜色将会进行过变化，请尽量将变色的方块选择出来吧！
                </v-card-text>
              </v-card>
            </v-col>
          </v-col>
        </v-row>
        <!-- Dialog for displaying messages -->
        <v-dialog v-model="dialog" max-width="400" @before-open="resetButtonColors">
          <v-alert :value="true" :type="dialogType">
            <v-card-title>{{ dialogTitle }}</v-card-title>
            <v-card-text>{{ dialogText }}</v-card-text>
            <v-card-actions>
              <v-btn @click="dialog = false; startNextRound()">OK</v-btn>
            </v-card-actions>
          </v-alert>
        </v-dialog>
      </v-container>
    </v-main>
  </v-card>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';

const props = defineProps(['someProp']);

const number = ref(Array.from({ length: 9 }, (_, index) => ({
  number: index + 1,
  color: 'white',
  originalColor: 'white',
})));
import robot from '../component/robot.vue'
const isAction = ref<boolean>(false);
const count = ref<number>(0); // 计算点击次数
let timer: NodeJS.Timeout | null = null; // 计时
const nowTime = ref<string>('00:00:00'); // 时间显示
let hour = ref<number>(0);
let minutes = ref<number>(0);
let seconds = ref<number>(0);
const correctButtonIndices = ref<Array<number>>([]);
const orangeButtonIndices = ref<Array<number>>([]);
const dialog = ref<boolean>(false);
const dialogTitle = ref<string>('');
const dialogText = ref<string>('');
const dialogType = ref<'error' | 'success' | 'warning' | 'info' | undefined>(undefined);
const gameInProgress = ref<boolean>(false);
const consecutiveCorrectAnswers = ref<number>(0);
const consecutiveIncorrectAnswers = ref<number>(0);
const maxIncorrectAttempts = ref<number>(3); // 最大错误尝试次数
const difficulty = ref<number>(1);

const isButtonsDisabled = computed(() => !gameInProgress.value);

const actionGame = () => {


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
const handleButtonClick = (index: number) => {
  if (!gameInProgress.value) {
    return;
  }

  const selectedButton = number.value[index];

  if (orangeButtonIndices.value.includes(index)) {
    if (difficulty.value === 1) {
      // 难度1：点击其中一个按钮即可
      dialogType.value = 'success'; // 设置为成功类型
      dialogTitle.value = '选择正确！';
      dialogText.value = '点击了正确的按钮。';
      dialog.value = true;
      consecutiveCorrectAnswers.value++;

      if (consecutiveCorrectAnswers.value === 3 && difficulty.value === 1) {
        dialogTitle.value = '难度提升';
        dialogText.value = '连续三次选择正确，难度提升至2！';
        dialog.value = true;
        difficulty.value = 2;
        consecutiveCorrectAnswers.value = 0; // 重置连续正确次数
      }
    } else if (difficulty.value === 2) {
      // 难度2：点击其中一个按钮
      if (correctButtonIndices.value.includes(index)) {
        // 如果点击的按钮在正确按钮数组中
        correctButtonIndices.value = correctButtonIndices.value.filter(
          (i) => i !== index
        );
        if (correctButtonIndices.value.length === 0) {
          // 如果正确按钮数组为空，说明两个按钮都已点击正确
          dialogType.value = 'success'; // 设置为成功类型
          dialogTitle.value = '成功通过';
          dialogText.value = '两个按钮都被正确点击。';
          dialog.value = true;
          gameInProgress.value = false;
        }
      } else {
        // 如果点击的按钮不在正确按钮数组中
        dialogType.value = 'error'; // 设置为错误类型
        dialogTitle.value = '选择错误';
        dialogText.value = '请重新选择！';
        dialog.value = true;
        consecutiveCorrectAnswers.value = 0;
        correctButtonIndices.value = [];
        consecutiveIncorrectAnswers.value++; // 累计错误尝试次数

        if (
          consecutiveIncorrectAnswers.value === maxIncorrectAttempts.value
        ) {
          gameInProgress.value = false;
          dialogTitle.value = '游戏结束';
          dialogText.value = '错误次数达到上限，游戏结束！';
          dialog.value = true;
        }
      }
    }
  } else {
    dialogType.value = 'error'; // 设置为错误类型
    dialogTitle.value = '选择错误';
    dialogText.value = '请重新选择！';
    dialog.value = true;
    consecutiveCorrectAnswers.value = 0;
    correctButtonIndices.value = []; // 修复在难度1时清空正确按钮数组
    consecutiveIncorrectAnswers.value++;

    if (
      consecutiveIncorrectAnswers.value === maxIncorrectAttempts.value
    ) {
      gameInProgress.value = false;
      dialogTitle.value = '游戏结束';
      dialogText.value = '错误次数达到上限，游戏结束！';
      dialog.value = true;
    }
  }
};

const startNextRound = () => {
  nowTime.value = '00:00:00';
  hour = ref(0);
  minutes = ref(0);
  seconds = ref(0);
  if (timer) {
    clearInterval(timer);
  }
  timer = setInterval(startTimer, 1000);
  gameInProgress.value = true;
  consecutiveIncorrectAnswers.value = 0; // 重置错误尝试次数

  number.value.forEach((button) => {
    button.color = button.originalColor;
  });

  orangeButtonIndices.value = [];
  correctButtonIndices.value = [];

  // 难度1：每次变色一个按钮
  // 难度2：每次变色两个按钮
  const numOrangeButtons = difficulty.value === 1 ? 1 : 2;

  for (let i = 0; i < numOrangeButtons; i++) {
    let randomIndex;
    do {
      randomIndex = Math.floor(Math.random() * number.value.length);
    } while (orangeButtonIndices.value.includes(randomIndex));

    number.value[randomIndex].color = 'orange';
    orangeButtonIndices.value.push(randomIndex);

    if (difficulty.value === 2) {
      correctButtonIndices.value.push(randomIndex);
    }
  }

  setTimeout(() => {
    orangeButtonIndices.value.forEach((index) => {
      number.value[index].color = 'white';
    });
  }, 2000);
};

const resetButtonColors = () => {
  // 在弹窗显示之前重置按钮颜色
  number.value.forEach((button, index) => {
    if (orangeButtonIndices.value.includes(index)) {
      button.color = 'white';
    }
  });
};

const emits = defineEmits(['eventName']);

</script>


<style>
.div-class {
  width:34vh;
//height:60vh;
  cursor: pointer;
  margin-top: auto;
  margin-left: auto;
}

.box-class {
  border: 1px solid #428bca;
  width:10vh;
  height:10vh;
  float: left;
  margin: 0.2vh;
  border-radius: 10%;
  background-color: #428bca;
  font-size: 2.5rem;
  line-height: 10vh;
  color: white;
}

.span-class {
  font-size: 23px;
  align-items: center;
  justify-content: center;
}
.action-button-class {
  margin-top: 2vh;
  width: 15vh;
  height: 5vh;
  font-size: 2vh;
}
</style>
