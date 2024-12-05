<script lang="ts" setup>
import CopyLabel from "@/components/common/CopyLabel.vue";

const loading = ref(true);

const headers = [
  {text: "学员id", align: "start", value: "id"},
  {
    text: "学员名",
    sortable: false,
    value: "user",
  },
  {text: "完成情况", value: "priority"},
  // {text: "是否查看报告", value: "status"},
  {text: "完成时间", value: "date"},
  {text: "", sortable: false, align: "right", value: "action"},
];
const items = [
  {
    id: "423",
    user: {
      name: "John Simon",
      email: "johnsimon@blobhill.com",
      avatar: "https://i.pravatar.cc/150?img=1",
    },
    date: "2023-05-10",
    priority: "Low",
    // status: "OPEN",
  },
  {
    id: "424",
    user: {
      name: "Greg Cool J",
      email: "cool@caprimooner.com",
      avatar: "https://i.pravatar.cc/150?img=2",
    },
    date: "2023-05-11",
    priority: "High",
    // status: "CLOSED",
  },
  {
    id: "425",
    user: {
      name: "Samantha Bush",
      email: "bush@catloveisstilllove.com",
      avatar: "https://i.pravatar.cc/150?img=3",
    },
    date: "2023-05-11",
    priority: "Low",
    // status: "CLOSED",
  },
  {
    id: "426",
    user: {
      name: "Ben Howard",
      email: "ben@indiecoolers.com",
      avatar: "https://i.pravatar.cc/150?img=4",
    },
    date: "2023-05-12",
    priority: "Low",
    // status: "OPEN",
  },
  {
    id: "427",
    user: {
      name: "Jack Candy",
      email: "jack@candylooove.com",
      avatar: "https://i.pravatar.cc/150?img=5",
    },
    date: "2023-05-13",
    priority: "High",
    // status: "OPEN",
  },
];

const open = (item) => {
};

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});
</script>

<template>
  <!-- loading spinner -->
  <div
    v-if="loading"
    class="h-full d-flex flex-grow-1 align-center justify-center"
  >
    <v-progress-circular color="primary" indeterminate></v-progress-circular>
  </div>
  <div v-else>
    <h6 class="text-h6 font-weight-bold pa-5 d-flex align-center">
      <span class="flex-fill">今日完成情况</span>
    </h6>
    <v-table class="pa-3">
      <thead>
      <tr>
        <th v-for="header in headers" :key="header.text" class="text-left">
          {{ header.text }}
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in items" :key="item.id">
        <td class="font-weight-bold">
          <copy-label :text="`# ${item.id}`"/>
        </td>
        <td>
          <copy-label :text="item.user.email"/>
        </td>
        <td>
          <v-chip
            :color="item.priority === 'High' ? 'pink' : 'primary'"
            class="font-weight-bold"
            size="small"
          >
            {{ item.priority }}
          </v-chip
          >
        </td>

        <!--        <td class="font-weight-bold">-->
        <!--          <div v-if="item.status === 'CLOSED'" class="text-secondary">-->
        <!--            <v-icon color="secondary" size="small">mdi-circle-medium</v-icon>-->
        <!--            <span>Closed</span>-->
        <!--          </div>-->
        <!--          <div v-if="item.status === 'OPEN'" class="text-success">-->
        <!--            <v-icon color="success" size="small">mdi-circle-medium</v-icon>-->
        <!--            <span>Open</span>-->
        <!--          </div>-->
        <!--        </td>-->
        <td>{{ item.date }}</td>
        <td>
          <v-btn
            elevation="4"
            size="small"
            variant="elevated"
            @click="open(item)"
          >
            打开报告
          </v-btn>
        </td>
      </tr>
      </tbody>
    </v-table>
  </div>
</template>

<style lang="scss" scoped>
.v-table {
  table {
    padding: 4px;
    padding-bottom: 8px;

    th {
      text-transform: uppercase;
      white-space: nowrap;
    }

    td {
      border-bottom: 0 !important;
    }

    tbody {
      tr {
        transition: box-shadow 0.2s, transform 0.2s;

        &:not(.v-data-table__selected):hover {
          box-shadow: 0 3px 15px -2px rgba(0, 0, 0, 0.12);
          transform: translateY(-4px);
        }
      }
    }
  }
}
</style>
