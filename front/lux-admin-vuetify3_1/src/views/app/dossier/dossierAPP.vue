<script setup lang="ts">
import { faker } from "@faker-js/faker";
import moment from "moment";
import {ref} from "vue";

const chooseColor = () => {
  let colors = ["red", "indigo", "blue", "cyan", "teal"];
  let randomColor = colors[Math.floor(Math.random() * colors.length)];
  return randomColor;
};

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
    //渠道来源
    channel:"市场活动",
    //信息来源
    information:"户外派单",
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
    ldate: moment(faker.date.past()).format("YYYY/MM/DD"),
    //
    role: faker.name.jobTitle(),
    rolestatus: chooseColor(),
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
const editedItem = ref({
  id: "",
  consultantname:"",
  //生日
  birthday:"",
  //年龄
  age:"",
  gender:"",
  //渠道来源
  channel:"",
  //信息来源
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
  ldate: "",
  role: "",
  rolestatus: "",
});
const defaultItem = ref({
  id: "",
  consultantname:"",
  //生日
  birthday:"",
  //年龄
  age:"",
  gender:"",
  //渠道来源
  channel:"",
  //信息来源
  information:"",
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
  //资源录入时间
  ldate: "",
  role: "",
  rolestatus: "",
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

function editItem(item: any) {
  editedIndex.value = desserts.value.indexOf(item);
  editedItem.value = Object.assign({}, item);
  dialog.value = true;
}
function deleteItem(item: any) {
  const index = desserts.value.indexOf(item);
  confirm("你确定要删除该信息吗？") &&
  desserts.value.splice(index, 1);
}

function close() {
  dialog.value = false;
  setTimeout(() => {
    editedItem.value = Object.assign({}, defaultItem.value);
    editedIndex.value = -1;
  }, 300);
}

function save() {
  if (editedIndex.value > -1) {
    Object.assign(desserts.value[editedIndex.value], editedItem.value);
  } else {
    desserts.value.push(editedItem.value);
  }
  close();
}

//Computed Property
const formTitle = computed(() => {
  return editedIndex.value === -1 ? "New Contact" : "Edit Contact";
});
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
            <v-dialog v-model="dialog" max-width="700">

              <v-card>
                <v-card-title class="pa-4 bg-secondary">
                  <span class="title text-white">{{ formTitle }}</span>
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
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="6">
                        <v-text-field
                          variant="outlined"
                          color="primary"
                          density="compact"
                          v-model="editedItem.consultantname"
                          label="咨询顾问"
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
                        ></v-select>
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
          <th class="text-subtitle-1 font-weight-semibold">渠道来源</th>
          <th class="text-subtitle-1 font-weight-semibold">信息来源</th>
          <th class="text-subtitle-1 font-weight-semibold">分配顾问时间</th>
          <th class="text-subtitle-1 font-weight-semibold">资源录入时间</th>
          <th class="text-subtitle-1 font-weight-semibold">报名套餐</th>
          <th class="text-subtitle-1 font-weight-semibold">报名日期</th>
          <th class="text-subtitle-1 font-weight-semibold">电话</th>
          <th class="text-subtitle-1 font-weight-semibold">总训练时长</th>
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
          <td>{{ item.channel }}</td>
          <td>{{ item.information }}</td>
          <td>{{ item.fdate }}</td>
          <td>{{ item.ldate}}</td>
          <td>{{ item.package }}</td>
          <td>{{ item.jdate }}</td>
          <td>{{ item.phone }}</td>
          <td>{{ item.total_time }}</td>

          <td>
            <div class="d-flex align-center">
              <v-tooltip text="编辑">
                <template v-slot:activator="{ props }">
                  <v-btn
                    color="blue"
                    icon
                    variant="text"
                    @click="editItem(item)"
                    v-bind="props"
                  >
                    <v-icon>mdi-pencil-outline</v-icon>

                  </v-btn>
                </template>
              </v-tooltip>
              <v-tooltip text="删除">
                <template v-slot:activator="{ props }">
                  <v-btn
                    icon
                    variant="text"
                    @click="deleteItem(item)"
                    v-bind="props"
                    color="error"
                  >
                    <v-icon>mdi-delete-outline</v-icon>
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
