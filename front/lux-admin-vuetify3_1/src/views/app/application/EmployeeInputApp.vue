<script lang="ts" setup>
import {faker} from "@faker-js/faker";
import moment from "moment";
import {ref, Ref} from 'vue';

const chooseColor = () => {
  let colors = ["red", "indigo", "blue", "cyan", "teal"];
  return colors[Math.floor(Math.random() * colors.length)];
};
//生成列表内容
const generateMessage = () => {
  return {
    // 生成4位id
    avatar: faker.internet.avatar(),
    username: faker.internet.userName(),
    usermail: faker.internet.email(),
    phone: faker.phone.number(),
    jdate: moment(faker.date.past()).format("YYYY/MM/DD"),
    role: faker.name.jobTitle(),
    rolestatus: chooseColor(),
  };
};
//生成id
const list = () => {
  return Array.from({length: 20}, (value, index) => ({
    id: "228369" + index + "",
    ...generateMessage(),
  }));
};

onMounted(() => {
  console.log(list());
});

const dialog = ref(false);
const search = ref("");
const rolesbg: Ref<string[]> = ref(["博士", "学术硕士", "专业硕士", "本科", "本科以下"]);
const gender: Ref<string[]> = ref(["男", "女"]);
const Marital: Ref<string[]> = ref(["是", "否"]);
const Pregnancy: Ref<string[]> = ref(["是", "否"]);
const desserts = ref(list());
const editedIndex = ref(-1);
const refForm = ref();
const editedItem = ref({
  id: "",
  avatar: "2.jpg",
  username: "",
  usermail: "",
  phone: "",
  jdate: "",
  role: "",
  rolestatus: "",
  gender: "",
  Marital: "",
  Pregnancy: "",
  school: "",
  Department: "",
  GraduateSchool: "",
  GraduateMajor: "",
  QqAccount: "",
  WeixinAccount: "",
  SuitColor: "",
  SuitSize: "",
  Remark: ""
});
//输入默认
const defaultItem = ref({
  id: "",
  avatar: faker.internet.avatar(),
  username: "",
  usermail: "",
  phone: "",
  jdate: "",
  role: "",
  rolestatus: "",
  gender: "",
  Marital: "",
  Pregnancy: "",
  school: "",
  Department: "",
  GraduateSchool: "",
  GraduateMajor: "",
  QqAccount: "",
  WeixinAccount: "",
  SuitColor: "",
  SuitSize: "",
  Remark: ""
});
//修改和编辑规则
const nameRules = [
  (v) => !!v || "选项是必填项",
  (v) => (v && v.length <= 10) || "必须少于10个字符",
];

//Methods
const filteredList = computed(() => {
  return desserts.value.filter((user: any) => {
    return user.username.toLowerCase().includes(search.value.toLowerCase());
  });
});

//编辑方法
function editItem(item: any) {
  editedIndex.value = desserts.value.indexOf(item);
  editedItem.value = Object.assign({}, item);
  dialog.value = true;
}
//信息查看方法

//删除方法
function deleteItem(item: any) {
  const index = desserts.value.indexOf(item);
  if(confirm("是否确实要删除此项目？")) {
    desserts.value.splice(index, 1);
  }
}
//取消关闭方法，因设计到多个数据而无法正确删除报错
function close() {
  dialog.value = false;
  setTimeout(() => {
    editedItem.value = Object.assign({}, defaultItem.value);
    editedIndex.value = -1;
  }, 300);
}

//保存方法
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
  return editedIndex.value === -1 ? "新员工信息录入" : "编辑查看员工信息";
});
</script>
<template>
  <v-container>
    <v-card>
      <v-card-text>
        <v-row>
          <v-col cols="12" lg="4" md="6">
            <v-text-field
              v-model="search"
              color="primary"
              density="compact"
              hide-details
              label="用户名查询"
              variant="outlined"
            ></v-text-field>
          </v-col>
          <v-col class="text-right" cols="12" lg="8" md="6">
            <v-dialog v-model="dialog" max-width="700">
              <template v-slot:activator="{ props }">
                <v-btn class="ml-auto" color="primary" v-bind="props">
                  <v-icon class="mr-2">mdi-account-multiple-plus</v-icon>
                  新增员工信息
                </v-btn>
              </template>
              <v-card>
                <v-card-title class="pa-4 bg-secondary">
                  <span class="title text-white">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-form
                    ref="form"
                    v-model="refForm"
                    class="mt-5"
                    lazy-validation
                  >
                    <v-row>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.username"
                          :counter="10"
                          :rules="nameRules"
                          color="primary"
                          density="compact"
                          label="员工姓名"
                          required
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.school"
                          :counter="10"
                          :rules="nameRules"
                          color="primary"
                          density="compact"
                          label="所属校区"
                          required
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.Department"
                          :counter="10"
                          :rules="nameRules"
                          color="primary"
                          density="compact"
                          label="所属部门"
                          required
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.role"
                          color="primary"
                          density="compact"
                          label="角色"
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.id"
                          color="primary"
                          density="compact"
                          label="Id"
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-select
                          v-model="editedItem.gender"
                          :items="gender"
                          color="primary"
                          density="compact"
                          label="性别"
                          variant="outlined"
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.phone"
                          color="primary"
                          density="compact"
                          label="手机号"
                          type="phone"
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.jdate"
                          color="primary"
                          density="compact"
                          label="加入时间"
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.GraduateSchool"
                          :counter="10"
                          :rules="nameRules"
                          color="primary"
                          density="compact"
                          label="毕业院校"
                          required
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.GraduateMajor"
                          :counter="10"
                          :rules="nameRules"
                          color="primary"
                          density="compact"
                          label="所学专业"
                          required
                          variant="outlined"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12" sm="4">
                        <v-select
                          v-model="editedItem.role"
                          :items="rolesbg"
                          color="primary"
                          density="compact"
                          label="学历"
                          variant="outlined"
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.QqAccount"
                          color="primary"
                          density="compact"
                          label="QQ号"
                          type=""
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.WeixinAccount"
                          color="primary"
                          density="compact"
                          label="微信号"
                          type=""
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.usermail"
                          color="primary"
                          density="compact"
                          label="员工邮箱"
                          type=""
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-select
                          v-model="editedItem.Marital"
                          :items="Marital"
                          color="primary"
                          density="compact"
                          label="是否已婚"
                          variant="outlined"
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-select
                          v-model="editedItem.Pregnancy"
                          :items="Pregnancy"
                          color="primary"
                          density="compact"
                          label="是否已孕"
                          variant="outlined"
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.SuitColor"
                          color="primary"
                          density="compact"
                          label="工服颜色"
                          type=""
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.SuitSize"
                          color="primary"
                          density="compact"
                          label="工服尺寸"
                          type=""
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="4">
                        <v-text-field
                          v-model="editedItem.Remark"
                          color="primary"
                          density="compact"
                          label="员工备注"
                          type=""
                          variant="outlined"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" md="8">
                        <v-file-input
                          color="primary"
                          density="compact"
                          label="一寸照片"
                          variant="outlined"
                        ></v-file-input>
                      </v-col>
                    </v-row>
                  </v-form>
                </v-card-text>
                <v-divider></v-divider>
                <!-- 按钮处-->
                <v-card-actions class="pa-4">
                  <v-spacer></v-spacer>
                  <v-btn color="error" @click="close">取消</v-btn>
                  <v-btn
                    :disabled="editedItem.username == '' || editedItem.usermail == '' "
                    color="secondary"
                    variant="flat"
                    @click="save"
                  >保存
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <v-card class="mt-2">
      <v-table>
        <thead>
        <tr>
          <th id="" class="text-subtitle-1 font-weight-semibold">Id</th>
          <th id="" class="text-subtitle-1 font-weight-semibold">所属校区</th>
          <th id="" class="text-subtitle-1 font-weight-semibold">所在部门</th>
          <th id="" class="text-subtitle-1 font-weight-semibold">员工名称和邮箱</th>
          <th id="" class="text-subtitle-1 font-weight-semibold">手机号</th>
          <th id="" class="text-subtitle-1 font-weight-semibold">加入时间</th>
          <th id="" class="text-subtitle-1 font-weight-semibold">角色</th>
          <th id="" class="text-subtitle-1 font-weight-semibold">操作</th>
        </tr>
        </thead>
        <tbody class="text-body-1">
        <tr v-for="item in filteredList" :key="item.id">
          <td class="font-weight-bold">{{ item.id }}</td>
          <td>滨海校区</td>
          <td>评测管理</td>
          <td>
            <div class="d-flex align-center py-1">
              <div>
                <v-img
                  :src="item.avatar"
                  class="rounded-circle img-fluid"
                  width="40"
                ></v-img>
              </div>
              <div class="ml-5">
                <p class="font-weight-bold">{{ item.username }}</p>
                <span class="d-block mt-1 text-caption textSecondary">{{
                    item.usermail
                  }}</span>
              </div>
            </div>
          </td>
          <td> {{ item.phone }}</td>
          <td> {{ item.jdate }}</td>
          <!--          角色-->
          <td>
            <v-chip
              :color="item.rolestatus"
              class="font-weight-bold"
              size="small"
            >
              {{ item.role }}
            </v-chip>
          </td>
          <!--          角色-->
          <td>
            <div class="d-flex align-center">
              <v-tooltip text="详细信息">
                <template v-slot:activator="{ props }">
                  <v-btn
                    color="green"
                    icon
                    v-bind="props"
                    variant="text"
                    @click="editItem(item)"
                  >
                    <v-icon>mdi-account</v-icon>
                  </v-btn>
                </template>
              </v-tooltip>
              <v-tooltip text="编辑">
                <template v-slot:activator="{ props }">
                  <v-btn
                    color="blue"
                    icon
                    v-bind="props"
                    variant="text"
                    @click="editItem(item)"
                  >
                    <v-icon>mdi-pencil-outline</v-icon>
                  </v-btn>
                </template>
              </v-tooltip>
              <v-tooltip text="删除">
                <template v-slot:activator="{ props }">
                  <v-btn
                    color="error"
                    icon
                    v-bind="props"
                    variant="text"
                    @click="deleteItem(item)"
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
