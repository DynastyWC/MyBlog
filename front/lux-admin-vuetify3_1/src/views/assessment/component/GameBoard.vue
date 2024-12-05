<template>
  <div class="game-board">
    <div v-for="(cell, index) in cells" :key="index" @click="handleCellClick(index)" :class="cellClasses[index]">
      <!-- 方格内容 -->
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted ,watch} from 'vue';

const cells = ref(Array(9).fill(false)); // 用于跟踪方格状态
const cellClasses = ref(Array(9).fill('cell'));

// 随机选择要变成蓝色的方格
const randomBlueCell = () => {
  const blueIndex = Math.floor(Math.random() * 9);
  cells.value[blueIndex] = true;
};
// 监视cells变化
watch(cells, (newCells) => {
  const index = newCells.findIndex((cell) => cell === true);
  if (index !== -1) {
    handleCellClick(index);
  }
});

// 处理点击事件
const handleCellClick = (index: number) => {
  if (cells.value[index]) {
    // 点击正确，将方格变成蓝色
    cellClasses.value[index] = 'blue-cell';
    cells.value[index] = false;
  } else {
    // 点击错误，将方格变成红色
    cellClasses.value[index] = 'red-cell';
  }
};

// 初始化游戏
onMounted(() => {
  // 随机选择要变成蓝色的方格，重复3次
  for (let i = 0; i < 3; i++) {
    randomBlueCell();
    setTimeout(() => {
      cells.value.fill(false);
    }, 1000);
  }
});
</script>

<style scoped>
.game-board {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  width: 900px;
  height: 900px;
  border: 2px solid black;
}

.cell {
  width: 100%;
  height: 100%;
  border: 2px solid black;
  background-color: white;
}

.blue-cell {
  width: 100%;
  height: 100%;
  border: 2px solid black;
  background-color: blue;
}

.red-cell {
  width: 100%;
  height: 100%;
  border: 2px solid black;
  background-color: red;
}
</style>
