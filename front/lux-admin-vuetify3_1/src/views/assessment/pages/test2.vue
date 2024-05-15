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
          <div class="div-class" :style="divClassWidth">
            <v-card v-for="(item, index) of number"
                 :key="index"
                 class="box-class"
                 @click="changeColor(item.number, index)"
                    :disabled="isButtonsDisabled"
            >
              <span class="span-class" >{{ item.number }}</span>
            </v-card>
          </div>
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
                  舒尔特方格测试
                </template>

                <v-card-text>
                  舒尔特方格(Schulte Grid)是在一张方形卡片上画上 1cm*1cm 的 25 个方格，格子内任意填写上阿拉伯数字 1 ~ 25 等共 25 个数字。训练时，要求被测者用手指按1~25的顺序依次指出其位置，同时诵读出声，施测者一旁记录所用时间。数完25个数字所用时间越短，注意力水平越高。
                </v-card-text>
              </v-card>
            </v-col>
          </v-col>
        </v-row>
        <v-dialog v-model="dialogVisible" title="Tips" width="20%" @before-close="handleClose" transition="fade">
          <template v-if="shouldShowAlert">
          <v-alert :type="alertType" >
            <v-card-title>测试提示</v-card-title>
            <v-card-text>{{ alertMessage }}</v-card-text>
            <v-btn v-if="shouldShowNextButton" @click="startNextDifficulty" color="primary">开始下一个难度</v-btn>
          </v-alert>
          </template>
        </v-dialog>


      </v-container>
    </v-main>
  </v-card>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import robot from '../component/robot.vue'
const number = ref<{ number: number; clicked: boolean }[]>([]);
const isAction = ref<boolean>(false);
const count = ref<number>(0); // 计算点击次数
let timer: NodeJS.Timeout | null = null; // 计时
const nowTime = ref<string>('00:00:00'); // 时间显示
let hour = ref<number>(0);
let minutes = ref<number>(0);
let seconds = ref<number>(0);
const dialogVisible = ref<boolean>(false); // 弹窗是否可见
const isRight = ref<boolean>(false); // 是否完成
const OK = ref<boolean>(false)
const currentDifficulty = ref(1); // 初始化为难度1
const isButtonsDisabled = ref(false); // 初始化为不禁用按钮方格

const alertType = computed(() => {
  if (OK.value) {
    return 'success';
  } else if (isRight.value) {
    return 'success';
  } else {
    return 'error';
  }
});


const alertMessage = computed(() => {
  if (OK.value) {
    return '恭喜你完成所有难度的测试！';
  } else if (isRight.value) {
    return `恭喜你完成难度${currentDifficulty.value} 共用时${nowTime.value}，继续下一个难度吧`;
  } else {
    return `啊哦~ 点错了哦 当前难度${currentDifficulty.value} 总共用时${nowTime.value}，继续下一项测试吧`;
  }
});

const shouldShowNextButton = computed(() => isRight.value && !OK.value);

const shouldShowAlert = computed(() => OK.value || isRight.value || (!OK.value && !isRight.value));
// 开始游戏
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

// 初始化数组
const getNumber = () => {
  const totalNumbers = currentDifficulty.value === 1 ? 9 : (currentDifficulty.value === 2 ? 16 : 25);
  const arr: { number: number; clicked: boolean }[] = [];
  for (let i = 1; i <= totalNumbers; i++) {
    arr.push({ number: i, clicked: false });
  }
  number.value = arr.sort(() => Math.random() > 0.5 ? -1 : 1);
};


// 选择正确则改变方块颜色
const changeColor = (item: number, index: number) => {
  if (isButtonsDisabled.value) {
    // 如果按钮方格被禁用，不执行任何操作
    return;
  }
  if (number.value[index].clicked) {
    // 已经点击过，不执行任何操作
    return;
  }
  count.value++;
  number.value[index].clicked = true;// 标记方格为已点击
  if (item === count.value) { // 点击正确
    const box = document.getElementsByClassName('box-class')[index] as HTMLElement;
    box.style.background = '#42ca64';
    box.style.border = '1px solid #42ca64';
    if (count.value === number.value.length) { // 游戏完成
      clearInterval(timer); // 清空计时器
      if (currentDifficulty.value < 3) { // 如果还有下一个难度
        // currentDifficulty.value++; // 切换到下一个难度
        isRight.value = true; // 完成提示
        dialogVisible.value = true; // 弹出提示框

      } else {
        OK.value = true// 已经完成最高难度，可以在这里添加适当的处理
        isButtonsDisabled.value = true;
        dialogVisible.value = true; // 展示提示框
      }
    }
  } else { // 点击错误
    isButtonsDisabled.value = true;
    const box = document.getElementsByClassName('box-class')[index] as HTMLElement;
    box.style.background = 'red';
    box.style.border = '1px solid red';
    clearInterval(timer); // 清空计时器
    dialogVisible.value = true; // 展示提示框
    isRight.value = false; // 未完成提示
  }
};
const startNextDifficulty = () => {
  dialogVisible.value = false; // 关闭弹窗

  // 使用 nextTick 确保在下一个 DOM 更新周期执行 startNextDifficulty
  nextTick(() => {
    currentDifficulty.value++;
    count.value = 0; // 清空计数
    isButtonsDisabled.value = false;
    resetBoxColors();
    getNumber(); // 重置方格
    isRight.value = false;
    actionGame(); // 开始下一个难度的测试
  });
};

// 重置方格颜色
const resetBoxColors = () => {
  const boxes = document.getElementsByClassName('box-class');
  for (let i = 0; i < boxes.length; i++) {
    const box = boxes[i] as HTMLElement;
    box.style.background = '#428bca';
    box.style.border = '1px solid #428bca';
  }
};


// 开启定时器
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

// 补零函数
const toZero = (timeNumber: number): string => {
  return timeNumber < 10 ? '0' + timeNumber : timeNumber.toString();
};

// 关闭弹窗
const handleClose = () => {
  dialogVisible.value = false; // 关闭弹窗
  isAction.value = false; // 重新进入页面
  count.value = 0; // 清空计数
  getNumber(); // 重置方格
  isRight.value = false;
};
const divClassWidth = computed(() => {
  if (currentDifficulty.value === 1) {
    return { width: '34vh' };
  } else if (currentDifficulty.value === 2) {
    return { width: '44vh' };
  } else {
    return { width: '54vh' };
  }
});
// 初始化数据
onMounted(() => {
  getNumber();
});
</script>

<style scoped >
.main {
  display: flex;
  justify-content: center;
}
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

.timer {
  font-size: 4vh;
  margin-bottom: 1vh;
}

.tip-class {
  border: 1px solid #a5aeb3;
  font-size: 2vh;
  text-align: initial;
  width: 120vh;
  padding: 0 3vh;
  margin-top: 5vh;
}

.action-button-class {
  margin-top: 2vh;
  width: 15vh;
  height: 5vh;
  font-size: 2vh;
}
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>
