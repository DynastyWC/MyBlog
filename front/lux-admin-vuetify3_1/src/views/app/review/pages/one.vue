<script setup lang="ts">

import { faker } from "@faker-js/faker";
import moment from "moment";
import {ref} from "vue";
import { VDatePicker } from 'vuetify/labs/VDatePicker'


const editDialog = ref(false);
const appointmentDialog = ref(false);
// 点击编辑按钮
function assdItem(item: any) {
  editedIndex.value = desserts.value.indexOf(item);
  assItem.value = { ...item };
  editDialog.value = true;
}

// 点击预约按钮
function editItem(item: any) {
  editedIndex.value = desserts.value.indexOf(item);
  editedItem.value = { ...item };
  appointmentDialog.value = true;
}

// 取消操作
function close() {
  editDialog.value = false;
  appointmentDialog.value = false;
  setTimeout(() => {
    editedItem.value = { ...defaultItem.value };
    editedIndex.value = -1;
  }, 300);
}

// 保存操作
function save() {
  if (editedIndex.value > -1) {
    Object.assign(desserts.value[editedIndex.value], editedItem.value);
  } else {
    desserts.value.push({ ...editedItem.value });
  }
  close();
}
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
    Trainer:"暂无"
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
const rolesbg = ref(["套餐1","套餐2","套餐3","套餐4",]);
const gender = ref(["男","女"])
const desserts = ref(list());
const editedIndex = ref(-1);
const refForm = ref();
const trainer = ref();

const editedItem = ref({
  id: "",
  gender:"",
  consultantname:"",
  //生日
  birthday:"",
  //年龄
  age:"",

  information:"",
  //报名类型
  type:"",
  //报名套餐
  package:"",
  //总训练时长
  total_time:"",
  avatar: "1.jpg",
  username: "",
  usermail: "",
  phone: "",
  jdate: "",
  //分配顾问时间
  fdate: "",
  //资源录入时间
  review:"",
  Trainer:""

});
const assItem = ref({
  id: "",
  consultantname:"",
  //生日
  birthday:"",
  //年龄
  age:"",
  gender:"",
  information:"",
  //报名类型
  type:"",
  //报名套餐
  package:"",
  //总训练时长
  total_time:"",
  avatar: "1.jpg",
  username: "",
  usermail: "",
  phone: "",
  jdate: "",
  //分配顾问时间
  fdate: "",
  //资源录入时间
  review:"",
  Trainer:"",
  Contact:""

});
const defaultItem = ref({
  id: "",
  consultantname:"",
  //生日
  birthday:"",
  //年龄
  age:"",
  gender:"",
  //报名类型
  type:"",
  //报名套餐
  package:"",
  //总训练时长
  total_time:"",
  username: "",
  avatar: faker.internet.avatar(),
  usermail: "",
  phone: "",
  jdate: "",
  //分配顾问时间
  fdate: "",
  review:"",
  Trainer:""
});

const nameRules = [
  (v) => !!v || "Name is required",
  (v) => (v && v.length <= 10) || "Name must be less than 10 characters",
];

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
          <v-col cols="12" lg="8" md="6" class="text-right">
            <v-dialog v-model="editDialog" max-width="700">

              <v-card>
                <v-card-title class="pa-4 bg-secondary">
                  <span class="title text-white">编辑</span>
                </v-card-title>

                <v-card-text>
                  <v-form
                    class="mt-5"
                    ref="form"
                    v-model="refForm"
                    lazy-validation
                  >
                    <v-row>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="assItem.id"
                          label="编号"
                          disabled
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          :rules="nameRules"
                          :counter="10"
                          required
                          v-model="assItem.username"
                          label="姓名"

                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="assItem.phone"
                          label="电话"
                          type="phone"

                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="assItem.birthday"
                          label="生日"
                          type="birthday"

                        ></v-text-field>
                      </v-col>

                      <v-col cols="12" sm="6">
                        <v-select
                          v-model="assItem.gender"
                          :items="gender"
                          color="primary"
                          density="compact"
                          label="性别"
                          variant="outlined"

                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="assItem.consultantname"
                          label="咨询顾问"

                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="12">
                        <v-select
                          variant="outlined"
                          color="primary"
                          density="compact"
                          :items="rolesbg"
                          v-model="assItem.package"
                          label="套餐选择"

                        ></v-select>
                        <v-select
                          variant="outlined"
                          color="primary"
                          density="compact"
                          :items="trainer"
                          v-model="assItem.Trainer"
                          label="训练师"
                        ></v-select>
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="assItem.Contact"
                          label="联系记录"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-form>
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions class="pa-4">
                  <v-spacer></v-spacer>
                  <v-btn color="error" @click="close">取消</v-btn>
                  <v-btn
                    color="secondary"
                    :disabled="
                      editedItem.username == '' || editedItem.usermail == ''
                    "
                    variant="flat"
                    @click="save"
                  >保存</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
          <v-col cols="12" lg="8" md="6" class="text-right">
            <v-dialog v-model="appointmentDialog" max-width="700">

              <v-card>
                <v-card-title class="pa-4 bg-secondary">
                  <span class="title text-white">预约</span>
                </v-card-title>

                <v-card-text>
                  <v-form
                    class="mt-5"
                    ref="form"
                    v-model="refForm"
                    lazy-validation
                  >
                    <v-row>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="editedItem.id"
                          label="编号"
                          disabled
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          :rules="nameRules"
                          :counter="10"
                          required
                          v-model="editedItem.username"
                          label="姓名"
                          disabled
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="editedItem.phone"
                          label="电话"
                          type="phone"
                          disabled
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="editedItem.birthday"
                          label="生日"
                          type="birthday"
                          disabled
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12" sm="6">
                        <v-select
                          v-model="editedItem.gender"
                          :items="gender"
                          color="primary"
                          density="compact"
                          label="性别"
                          variant="outlined"
                          disabled
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="editedItem.consultantname"
                          label="咨询顾问"
                          disabled
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="12">
                        <v-select
                          variant="outlined"
                          color="primary"
                          density="compact"
                          :items="rolesbg"
                          v-model="editedItem.package"
                          label="套餐选择"
                          disabled
                        ></v-select>
                        <v-select
                          variant="outlined"
                          color="primary"
                          density="compact"
                          :items="trainer"
                          v-model="editedItem.Trainer"
                          label="训练师"
                        ></v-select>
                        <v-select
                          variant="outlined"
                          color="primary"
                          density="compact"
                          :items="trainer"
                          v-model="editedItem.review"
                          label="预约日期"
                        >
                          <template>
                            <v-container>
                              <v-row justify="space-around">
                                <v-date-picker
                                  color="primary"
                                ></v-date-picker>
                              </v-row>
                            </v-container>
                          </template>
                        </v-select>

                      </v-col>
                    </v-row>
                  </v-form>
                </v-card-text>
                <v-divider></v-divider>
                <v-card-actions class="pa-4">
                  <v-spacer></v-spacer>
                  <v-btn color="error" @click="close">取消</v-btn>
                  <v-btn
                    color="secondary"
                    :disabled="
                      editedItem.username == '' || editedItem.usermail == ''
                    "
                    variant="flat"
                    @click="save"
                  >预约</v-btn
                  >
                </v-card-actions>
              </v-card>
            </v-dialog>
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
          <th class="text-subtitle-1 font-weight-semibold">预约</th>
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
              <p class="font-weight-bold">{{ item.Trainer }}</p>
              <span class="d-block mt-1 text-caption textSecondary">
                  {{item.review}}</span>
            </div>

          </td>


          <td>

            <div class="d-flex align-center">
              <v-tooltip text="编辑">
                <template v-slot:activator="{ props }">
                  <v-btn
                    color="blue"
                    icon
                    variant="text"
                    @click="assdItem(item)"
                    v-bind="props"
                  >
                    <v-icon>mdi-pencil-outline</v-icon>

                  </v-btn>
                </template>
              </v-tooltip>
              <v-tooltip text="预约">
                <template v-slot:activator="{ props }">
                  <v-btn
                    color="red"
                    icon
                    variant="text"
                    @click="editItem(item)"
                    v-bind="props"
                  >
                    <v-icon>mdi-cancel</v-icon>

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
