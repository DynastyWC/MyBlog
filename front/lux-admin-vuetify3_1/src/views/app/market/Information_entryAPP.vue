<script lang="ts" setup>
import { ref } from 'vue'
const input = ref('')
const gender = ref(["男","女"])


// 获取当前日期时间
function getCurrentDateTime() {
  const now = new Date();
  const year = now.getFullYear();
  const month = padZero(now.getMonth() + 1);
  const day = padZero(now.getDate());
  const hours = padZero(now.getHours());
  const minutes = padZero(now.getMinutes());
  const seconds = padZero(now.getSeconds());

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

// 补零函数
function padZero(number) {
  return number < 10 ? '0' + number : number.toString();
}

const currentDateTime = ref(getCurrentDateTime());
</script>
<template>
<v-container>
  <v-card>
    <v-card-text>
      <v-btn color="primary" class="ml-auto"  style="margin-right: 1%">
        暂存
      </v-btn>
      <v-btn color="primary" class="ml-auto" style="margin-right: 1%">
        转下一步
      </v-btn>
      <v-btn color="primary" class="ml-auto">
        删除
      </v-btn>
    </v-card-text>
  </v-card>
<v-card class="mt-2">
  <v-card-text>
    <v-form
      ref="form"
      class="mt-5"
      lazy-validation
    >
      <v-row>
        <v-col cols="12" sm="6">
          录入人员：
          <v-text-field
            color="primary"
            density="compact"
            label="guowudi"
            variant="outlined"
            disabled
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="6">
          录入时间：
          <v-text-field
            color="primary"
            density="compact"

            variant="outlined"
            :value="currentDateTime"
            disabled
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" sm="6">
          会员基本信息
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" sm="3">
          <v-text-field
            color="primary"
            density="compact"
            label="姓名"
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="3">
          <v-select
            :items="gender"
            color="primary"
            density="compact"
            label="性别"
            variant="outlined"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="3">
          <v-text-field
            color="primary"
            density="compact"
            label="生日"
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="3">
          <v-text-field
            color="primary"
            density="compact"
            label="年龄"
            disabled
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="6">
          <v-text-field
            color="primary"
            density="compact"
            label="学校"
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="6">
          <v-text-field
            color="primary"
            density="compact"
            label="家庭地址"
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="3">
          <v-select
            :items="information"
            color="primary"
            density="compact"
            label="信息来源类别"
            variant="outlined"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="3">
          <v-text-field
            color="primary"
            density="compact"
            label="信息来源"
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="3">
          <v-select
            :items="information"
            color="primary"
            density="compact"
            label="来源子通道"
            variant="outlined"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="3">
          <v-select
            :items="information"
            color="primary"
            density="compact"
            label="介绍会员姓名"
            variant="outlined"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="3">
          <v-select
            :items="information"
            color="primary"
            density="compact"
            label="介绍员工姓名"
            variant="outlined"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="3">
          <v-select
            :items="information"
            color="primary"
            density="compact"
            label="关联活动"
            variant="outlined"
          ></v-select>
        </v-col>
      </v-row>
      <v-form>
      <v-row>
        <v-col cols="12" sm="6">
          家长信息
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="12" sm="3">
          <v-select
            :items="information"
            color="primary"
            density="compact"
            label="关系"
            variant="outlined"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="3">
          <v-text-field
            color="primary"
            density="compact"
            label="姓名"
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="3">
          <v-text-field
            color="primary"
            density="compact"
            label="电话"
            variant="outlined"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="3">
          <v-text-field
            color="primary"
            density="compact"
            label="年龄"
            variant="outlined"
          ></v-text-field>
        </v-col>
      </v-row>

      </v-form>
    </v-form>
  </v-card-text>
</v-card>
</v-container>
</template>


