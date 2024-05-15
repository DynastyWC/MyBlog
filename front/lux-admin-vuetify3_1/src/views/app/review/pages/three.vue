<script setup lang="ts">
import { faker } from "@faker-js/faker";
import moment from "moment";
import {ref} from "vue";


const generateMessage = () => {
  return {
    // 生成4位id
    avatar: faker.internet.avatar(),
    //用户名
    username: faker.internet.userName(),
    //咨询顾问名
    consultantname:faker.internet.userName(),
    //生日
    birthday:moment(faker.date.past()).format("YYYY/MM/DD"),
    //年龄
    age:"6岁3个月",
    //性别
    gender:"暂无",
    //报名类型
    type:"初次报名",
    //报名套餐
    package:"套餐1",
    //总训练时长
    total_time:"104",
    usermail: faker.internet.email(),
    //用户电话
    phone: faker.phone.number(),
    //报名日期
    jdate: moment(faker.date.past()).format("YYYY/MM/DD"),
    //分配顾问时间
    fdate: moment(faker.date.past()).format("YYYY/MM/DD"),
    //资源录入时间
    review:"",
    Trainer:"暂无",
    Contact:"暂无"
  };
};

const list = () => {
  let list = [] as any[];
  list = Array.from({ length: 20 }, (value, index) => ({
    id: "#1000" + index + "",
    ...generateMessage(),
  }));
  return list;
};

onMounted(() => {
  console.log(list());
});

const dialog = ref(false);
const search = ref("");
const gender = ref(["男","女"])
const desserts = ref(list());
const editedIndex = ref(-1);
const trainer = ref();






//Methods
const filteredList = computed(() => {
  return desserts.value.filter((user: any) => {
    return user.username.toLowerCase().includes(search.value.toLowerCase());
  });
});


//Computed Property

</script>
<template>
  <v-container>
    <v-card>
      <v-card-text>
        <v-row>
          <v-col cols="12" lg="4" md="6">
            <v-text-field
              density="compact"
              v-model="search"
              label="查找"
              hide-details
              variant="outlined"
              color="primary"
            ></v-text-field>
          </v-col>

        </v-row>
      </v-card-text>
    </v-card>


    <v-card class="mt-2">
      <v-table class="mt-5">
        <thead>
        <tr>
          <th class="text-subtitle-1 font-weight-semibold">用户编号</th>
          <th class="text-subtitle-1 font-weight-semibold">用户姓名</th>
          <th class="text-subtitle-1 font-weight-semibold">性别</th>
          <th class="text-subtitle-1 font-weight-semibold">咨询顾问</th>
          <th class="text-subtitle-1 font-weight-semibold">分配顾问时间</th>
          <th class="text-subtitle-1 font-weight-semibold">报名套餐</th>
          <th class="text-subtitle-1 font-weight-semibold">报名日期</th>
          <th class="text-subtitle-1 font-weight-semibold">电话</th>
          <th class="text-subtitle-1 font-weight-semibold">总训练时长</th>
          <th class="text-subtitle-1 font-weight-semibold">联系记录</th>
        </tr>
        </thead>
        <tbody class="text-body-1">
        <tr v-for="item in filteredList" :key="item.id">
          <td class="font-weight-bold">{{ item.id }}</td>
          <td>

            <div >
              <p class="font-weight-bold">{{ item.username }}</p>
              <span class="d-block mt-1 text-caption textSecondary">
                  {{item.age }}{{item.birthday}}</span>
            </div>

          </td>
          <td>{{ item.gender}}</td>
          <td>{{ item.consultantname }}</td>

          <td>{{ item.fdate }}</td>

          <td>{{ item.package }}</td>
          <td>{{ item.jdate }}</td>
          <td>{{ item.phone }}</td>
          <td>{{ item.total_time }}</td>

          <td>

            <div >
              <span class="d-block mt-1 text-caption textSecondary">
                  {{item.Contact}}</span>
            </div>

          </td>

          <td>
            <div class="d-flex align-center">

              <v-tooltip text="评测">
                <template v-slot:activator="{ props }">
                  <v-btn
                    color="blue"
                    icon
                    variant="text"
                    to ="/assessment"
                    v-bind="props"
                    target="_blank"
                  >
                    <v-icon>mdi-account</v-icon>

                  </v-btn>
                </template>
              </v-tooltip>

            </div>
          </td>
        </tr>
        </tbody>
      </v-table>
    </v-card>
  </v-container>
</template>
