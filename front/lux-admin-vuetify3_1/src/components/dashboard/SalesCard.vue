<script lang="ts" setup>
import moment from "moment";
import {useTheme} from "vuetify";
import PercentTrend from "@/components/common/PercentTrend.vue";

const formatDate = (date: string) => {
  return date ? moment(date).format("D MMM") : "";
};

const props = defineProps({
  value: {
    type: Number,
    default: 0,
  },
  percentage: {
    type: Number,
    default: 0,
  },
  percentageLabel: {
    type: String,
    default: "vs. 上周",
  },
  series: {
    type: Array,
    default: () => [
      {
        name: "Sales",
        data: [11, 32, 45, 13],
      },
    ],
  },
  xaxis: {
    type: Object,
    default: () => ({
      type: "category",
      categories: [
        "2023-07-19T00:00:00.000Z",
        "2023-07-20T00:00:00.000Z",
        "2023-07-22T00:00:00.000Z",
        "2023-07-23T00:00:00.000Z",
      ],
      // tickAmount: 3
    }),
  },
  label: {
    type: String,
    default: "dashboard.sales",
  },
  actionLabel: {
    type: String,
    default: "View Report",
  },
  options: {
    type: Object,
    default: () => ({}),
  },
});

const {themes, current} = useTheme();
const chartOptions = computed(() => {
  const primaryColor = current.value.dark
    ? themes.value["dark"].colors.primary
    : themes.value["light"].colors.primary;

  return {
    chart: {
      height: 120,
      type: "area",
      sparkline: {
        enabled: true,
      },
      animations: {
        speed: 400,
      },
    },
    series: props.series,
    colors: [primaryColor],
    fill: {
      type: "solid",
      colors: [primaryColor],
      opacity: 0.15,
    },
    stroke: {
      curve: "smooth",
      width: 2,
    },
    xaxis: props.xaxis,
    tooltip: {
      followCursor: true,
      theme: "dark",
      custom: function ({ctx, series, seriesIndex, dataPointIndex, w}: any) {
        const seriesName = w.config.series[seriesIndex].name;

        return `<div class="rounded-lg pa-1 text-caption">
                <div class="font-weight-bold">${formatDate(
          w.globals.categoryLabels[dataPointIndex]
        )}</div>
                <div>${series[seriesIndex][dataPointIndex]} ${seriesName}</div>
              </div>`;
      },
    },
    ...props.options,
  };
});

const loading = ref(true);

onMounted(() => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});
</script>
<template>
  <v-card class="d-flex flex-grow-1 bg-primary-darken-4 pa-3" theme="dark">
    <!-- loading spinner -->
    <div v-if="loading" class="d-flex flex-grow-1 align-center justify-center">
      <v-progress-circular color="primary" indeterminate></v-progress-circular>
    </div>

    <!-- information -->
    <div v-else class="d-flex flex-column flex-grow-1">
      <v-card-title class="d-flex">
        <div class="font-weight-bold">{{ $t(label) }}</div>
        <v-spacer></v-spacer>
        <v-btn
          class="font-weight-bold"
          color="primary"
          variant="text"
          @click="$emit('action-clicked')"
        >{{ actionLabel }}
        </v-btn
        >
      </v-card-title>

      <div class="d-flex flex-column flex-grow-1">
        <div class="pa-2">
          <div class="text-h4">
            {{ }}
          </div>
          <div class="text-primary mt-1">
            {{ }}
            {{ $t("dashboard.lastweek") }}
          </div>
        </div>

        <v-spacer></v-spacer>

        <div class="px-2 pb-2">
          <div class="title mb-1 font-weight-bold">
            {{ $t("dashboard.weeklySales") }}
          </div>
          <div class="d-flex align-center">
            <div class="text-h4">
              {{ }}
            </div>
            <v-spacer></v-spacer>
            <div class="d-flex flex-column text-right">
              <div class="font-weight-bold">
                <percent-trend :value="percentage"/>
              </div>
              <div class="text-caption">{{ percentageLabel }}</div>
            </div>
          </div>
        </div>
      </div>

      <apexchart
        :options="chartOptions"
        :series="series"
        height="120"
        type="area"
      ></apexchart>
    </div>
  </v-card>
</template>
