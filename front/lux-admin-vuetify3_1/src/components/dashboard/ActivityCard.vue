<script lang="ts" setup>
import {Icon} from "@iconify/vue";
import moment from "moment";

const loading = ref(false);
const username = ref("yangjiakai");
const activityList = ref([
  {
    id: "29003260817",
    type: "任务完成",
    user: "评测师",
    avatar: "https://avatars.githubusercontent.com/u/35951244?",
    repo: "",
    content:
      "<p> 今日任务</p><br/><div><span class='mr-1'>✅</span> 学员1</div><div><span class='mr-1'>✅</span> 学员2</div><div><span class='mr-1'>✅</span> 学员3</div>",
    action: "Commit",
    created_at: "2023-05-12T14:06:59Z",
  },
]);

const mockAcitvitys = [
  {
    id: "29003260817",
    type: "任务完成",
    user: "评测师",
    avatar: "https://avatars.githubusercontent.com/u/35951244?",
    repo: "",
    content:
      "<p> 今日任务</p><br/><div><span class='mr-1'>✅</span> 学员1</div><div><span class='mr-1'>✅</span> 学员2</div><div><span class='mr-1'>✅</span> 学员3</div>",
    action: "Commit",
    created_at: "2023-05-12T14:06:59Z",
  },
  {
    id: "29003260817",
    type: "任务完成",
    user: "评测师",
    avatar: "https://avatars.githubusercontent.com/u/35951244?",
    repo: "",
    content:
      "<p> 今日任务</p><br/><div><span class='mr-1'>✅</span> 学员1</div><div><span class='mr-1'>✅</span> 学员2</div><div><span class='mr-1'>✅</span> 学员3</div>",
    action: "Commit",
    created_at: "2023-05-12T14:06:59Z",
  },
];

const getPublicEvent = async () => {
  loading.value = true;
  // const response = await getPublicEventsApi(username.value);

  // activityList.value = response.data
  //   .map((activity) => {
  //     return {
  //       id: activity.id,
  //       type: activity.type,
  //       user: activity.actor.display_login,
  //       avatar: activity.actor.avatar_url,
  //       repo: activity.repo?.name,
  //       content: getContent(activity),
  //       action:
  //         activity.type === "IssuesEvent" ? activity.payload.action : "Commit",
  //       created_at: activity.created_at,
  //     };
  //   })

  activityList.value = mockAcitvitys;
  setTimeout(() => {
    loading.value = false;
  }, 1000);
};

const getContent = (activity: any) => {
  if (activity.type === "PushEvent") {
    return convertToHtml(activity.payload.commits[0].message);
  } else if (activity.type === "CreateEvent") {
    return activity.payload.ref_type;
  } else if (activity.type === "IssuesEvent") {
    return activity.payload.issue.title;
  } else {
    return "";
  }
};

const convertToHtml = (text) => {
  const lines = text.split("\n");
  let html = "";

  lines.forEach((line) => {
    if (line.startsWith("- ")) {
      html += `<div><span class='mr-1'>✅</span> ${line.slice(2)}</div>`;
    } else if (line.trim() === "") {
      html += "<br/>";
    } else {
      html += `<p>${line}</p>`;
    }
  });

  return html;
};

const getTagColor = (activity: any) => {
  if (activity.type === "PushEvent") {
    return "green";
  } else if (activity.type === "IssuesEvent") {
    return "red";
  } else {
    return "blue";
  }
};

onMounted(() => {
  getPublicEvent();
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
    <h6 class="text-h6 pa-5 d-flex align-center">
      <span class="flex-fill font-weight-bold">今日情况</span>
      <v-menu location="bottom end" transition="slide-x-transition">
        <template v-slot:activator="{ props }">
          <v-btn
            class="my-n2"
            color="primary"
            icon="mdi-dots-vertical"
            rounded
            size="small"
            v-bind="props"
            variant="text"
          ></v-btn>
        </template>
        <v-list density="compact">
          <v-list-item @click="$emit('edit')">
            <v-list-item-title class="d-inline-flex align-center">
              <Icon
                :horizontalFlip="true"
                :rotate="2"
                :verticalFlip="true"
                class="mr-1"
                icon="flat-color-icons:refresh"
              />
              <span> Refresh</span>
            </v-list-item-title>
          </v-list-item>
          <v-list-item @click="$emit('delete')">
            <v-list-item-title class="d-inline-flex align-center">
              <Icon
                :horizontalFlip="true"
                :inline="true"
                :rotate="2"
                :verticalFlip="true"
                class="mr-1"
                icon="icon-park:clear-format"
              />
              Clear
            </v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </h6>
    <perfect-scrollbar class="timeline-container">
      <v-timeline
        class="time-line text-body-2"
        density="compact"
        side="end"
        truncate-line="start"
      >
        <v-timeline-item
          v-for="activity in activityList"
          :key="activity.id"
          size="small"
        >
          <template v-slot:icon>
            <v-avatar>
              <img :src="activity.avatar"/>
            </v-avatar>
          </template>
          <template v-slot:opposite>
            <span>{{ moment(activity.created_at).format("MM,DD hh:mm") }}</span>
          </template>
          <div class="mb-1">
            <span class="text-h6 font-weight-bold">
              {{ activity.user }}
            </span>
            <span class="ml-2 text-grey">{{
                moment(activity.created_at).format("MM,DD hh:mm")
              }}</span>
          </div>

          <v-card max-width="500">
            <v-card-subtitle class="pt-4">
              <v-chip
                :color="getTagColor(activity)"
                class="mr-2 font-weight-bold"
                label
                size="small"
              >
                <span>{{ activity.type }}</span>
              </v-chip>
              <span class="text-body-2">{{ activity.repo }}</span>
            </v-card-subtitle>
            <v-card-text>
              <div v-html="activity.content"></div>
            </v-card-text>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </perfect-scrollbar>
  </div>
</template>

<style lang="scss" scoped>
.timeline-container {
  height: 360px;
  overflow: scroll;
}

.time-line {
  margin-left: 60px;
}
</style>
