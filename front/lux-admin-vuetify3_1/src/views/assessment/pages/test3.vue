<template>
  <v-card
    variant="flat"
    height="100vh"
    class=" justify-center align-center text-center"
    flex
  >
    <v-main class="main">
    <v-container>
      <v-row v-if="isAction">
        <v-col cols="12">
          <v-card class="timer">
            <v-card-text>{{ nowTime }}</v-card-text>
          </v-card>
        </v-col>
        <div class="content">
          <div class="robot">
            <robot></robot>
          </div>
          <div class="card-grid" :style="setGridSize()">
            <v-card class="grid-container" :style="setGridSize()">
              <v-row class="grid-row" v-for="(row, rowIndex) in getRowCount()" :key="rowIndex" :style="setRow()">
                <v-col class="grid-col" v-for="(col, colIndex) in getRowCount()" :key="colIndex" :style="setCol()">
                  <div class="grid-cell" :style="getCellStyle()" @click="handleClick(rowIndex, colIndex)">
                  </div>
                </v-col>
              </v-row>
            </v-card>
          </div>
        </div>

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
                寻找不同
              </template>

              <v-card-text>
                该测试总共分为三个难度，简单，中等，困难，测试者需要从图片中找到与其他图片不同的内容，并选择出来！
                PS:可能会有多个哦!
              </v-card-text>
            </v-card>
          </v-col>
        </v-col>
      </v-row>
      <v-dialog v-model="dialog" max-width="400">
            <v-alert v-if="success" type ="success">
              <v-card-title>测试提示</v-card-title>
              <v-card-text> 恭喜通关！用时：{{ nowTime }}</v-card-text>
              <v-card-actions>
                <v-btn @click="closeDialog">确定</v-btn>
              </v-card-actions>
            </v-alert>
            <v-alert v-else type ="error">
              <v-card-title>测试提示</v-card-title>
              <v-card-text v-if="chances > 0">选择错误，还有 {{ chances }} 次机会！</v-card-text>
              <v-card-text v-else> 机会用完，测试结束！</v-card-text>
              <v-card-actions>
                <v-btn @click="closeDialog">确定</v-btn>
              </v-card-actions>
            </v-alert>
      </v-dialog>
    </v-container>
    </v-main>
  </v-card>
</template>
<script setup lang="ts">
import { ref, onMounted ,Ref} from 'vue';
import robot from '../component/robot.vue'
const cellRefs: Ref<HTMLDivElement[]> = ref([]);
const success = ref(false);
const error = ref(false);
const dialogText = ref("请选择与其他图案不同的图案哦");
const isAction = ref<boolean>(false);
let timer: NodeJS.Timeout | null = null; // 计时
const nowTime = ref<string>('00:00:00'); // 时间显示
let hour = ref<number>(0);
let minutes = ref<number>(0);
let seconds = ref<number>(0);
const dialog = ref(false); // 控制对话框的显示和隐藏
const difficulty = ref("easy"); // 初始难度为简单
const chances = ref<number>(2); // 初始有两次机会
const gridSizes = {
  easy: { width: '400px', height: '400px' },
  medium: { width: '500px', height: '500px' },
  hard: { width: '600px', height: '600px' },
};
const getRowCount = () => {
  if (difficulty.value === "easy") {
    return 4;
  } else if (difficulty.value === "medium") {
    return 5;
  } else if (difficulty.value === "hard") {
    return 6;
  } else {
    return 4; // 默认值，可以根据实际情况修改
  }
}
const setRow= () => {
  const Size = (1 / getRowCount())*100;
  const a = Size.toFixed(2) + '%';
  console.log(`${a}`)
  return {
    height: a
  };
};
const setCol= () => {
  const Size = 1 / getRowCount();
  const a = Size.toFixed(2) + '%';
  return {
    width: a
  };
};
const setGridSize = () => {
  const gridSize = gridSizes[difficulty.value];
  return {
    width: gridSize.width,
    height: gridSize.height,
    position: 'relative',
  };
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
const handleClick = (rowIndex, colIndex) => {
  let err = true;
  console.log(`左上角相对坐标：${rowIndex * 100}, ${colIndex * 100}`);
  if (colIndex === 3 && rowIndex === 3) {
    err = false
    if (difficulty.value === "easy") {
      difficulty.value = "medium"; // 切换到中等难度
    } else if (difficulty.value === "medium") {
      difficulty.value = "hard"; // 切换到困难难度
    }else {
      success.value = true;
      dialog.value = true;
    }
  }
  if (err) {
    if (chances.value > 0) {
      chances.value -= 1;
      showErrorMessage();
    } else {
      showGameOverMessage();
    }
  }
};
const showErrorMessage = () => {
  dialog.value = true; // 打开对话框，显示错误消息
};

const showGameOverMessage = () => {
  dialog.value = true; // 打开对话框，显示机会用完消息
};


const closeDialog = () => {
  dialog.value = false; // 关闭对话框
};
// const createGift = (color,rowIndex, colIndex) => {
//   console.log('Creating a gift with color: ' + color);
//   const giftElement = document.createElement('div');
//   giftElement.classList.add('gift', `${color}-gift`);
//   const cellIndex = rowIndex * 4 + colIndex;
//   console.log(`第${cellIndex}块`)
//   cellRefs.value[cellIndex].appendChild(giftElement); // Assuming 4x4 grid and target cell is at (3, 3)
// };

const getCellStyle = () => {

  const gridSize = gridSizes[difficulty.value];
  console.log(`${gridSize.width}`)
  // const cellWidth = gridSize.width.replace('px', '') / getRowCount();
  return {
    width: gridSize.width,
    height: gridSize.height,
    margin: '0',  // 设置 margin 为零
    padding: '0', // 设置 padding 为零
    position: 'relative',
  };
};

onMounted(() => {
  cellRefs.value = Array.from(document.querySelectorAll('.grid-cell'));
  console.log('cellRefs:', cellRefs.value);
});

</script>


<style scoped>
.main {
  display: flex;
  justify-content: center;
}
.card-grid {
  width:34vh;
//height:60vh;
  cursor: pointer;
  margin-top: auto;
  margin-left: auto;
}

.grid-container {
  width: 400px; /* 默认大小 */
  height: 400px;

  border: none;
  overflow: hidden;
  background-image: url("/ertongimage/text3_1.png");

}


.grid-row {
  height: 25%;
  border: none;
}

.grid-col {
  width: 25%;
  border: none;
}

.grid-cell {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  border: 1px solid #000;
}

.gift {
  position: absolute;
  width: 100px;
  height: 100px;
  border: 10px solid transparent;
  border-radius: 50%;
  animation: bounce 1s ease infinite;
}

.red-gift {
  border-color: red;
}

.green-gift {
  border-color: green;
}

@keyframes bounce {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.1);
  }
  100% {
    transform: scale(1);
  }
}
.robot {
  display: flex;
  justify-content: flex-start; /* 左对齐 */
  align-items: center; /* 垂直居中 */
  width: 50%; /* 你可以根据需要调整宽度 */
  padding: 20px; /* 可能需要一些内边距来调整机器人与网格之间的距离 */
}
.content {
  display: flex;
  justify-content: space-between; /* 或其他你想要的对齐方式，如 space-around, flex-start, flex-end */
  align-items: center;
  width: 100%; /* 占满容器的宽度 */
}
.dialog-box {
  background-color: #f0f0f0;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  //margin-left: 20px; /* 调整与机器人的距离 */
  font-size: 14px;
}
.action-button-class {
  margin-top: 2vh;
  width: 15vh;
  height: 5vh;
  font-size: 2vh;
}
.result-screen {
  font-size: 18px;
  margin-top: 20px;
  text-align: center;
  color: #333;
}
.no-margin {
  margin: 0;
}
</style>
