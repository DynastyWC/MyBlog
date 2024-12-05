<template>
  <v-container>
    <!-- 最上面的导航栏 -->
    <!-- 最上面的导航栏 -->
    <!-- 最上面的导航栏 -->
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

    <!-- 中间的下拉选择框 -->
    <!-- 中间的下拉选择框 -->
    <!-- 中间的下拉选择框 -->

      <v-card class="mt-2">
      <v-form
        ref="form"
        class="mt-5"
        lazy-validation
      >
      <v-row class="pa-1" >
        <v-col cols="12" md="4">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="* 流程标题"
          ></v-text-field>
        </v-col>

        <v-col cols="12" md="2">
         <v-text-field
           variant="outlined"
           color="primary"
           density="compact"
           label="所在训练中心："
         ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
         <v-text-field
           variant="outlined"
           color="primary"
           density="compact"
           label="用户："
         ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
         <v-text-field
           variant="outlined"
           color="primary"
           density="compact"
           label="申请时间："
         ></v-text-field>
        </v-col>

        <v-col cols="12" md="2">
          <v-select
            v-model="selectedItem"
            :items="apply"
            color="primary"
            density="compact"
            variant="outlined"
            label="报名类型"
          ></v-select>
        </v-col>
      </v-row>
        <v-spacer height="0"></v-spacer>
       <v-row class="pa-1" >
        <v-col cols="12" md="2">
          <v-select
            :items="Name"
            color="primary"
            density="compact"
            variant="outlined"
            label="会员名称"
          ></v-select>
        </v-col>
        <v-col cols="12" md="2">
          <v-select
            :items="yes"
            color="primary"
            density="compact"
            variant="outlined"
            label="是否转套餐"
          ></v-select>
        </v-col>

        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="应交金额"
            model-value="0"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="转项目金额"
            model-value="0"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="订单金额"
            model-value="0"
          ></v-text-field>
        </v-col>

        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="代金券金额"
            model-value="0"
          ></v-text-field>
        </v-col>
        </v-row>
      <v-row class="pa-1">
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="实际缴费"
            model-value="0"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="享受折扣"
            model-value="0"
          ></v-text-field>
        </v-col>

        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="实际折扣率"
            model-value="0.00"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="赠送训练时长"
            model-value="0"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="报名总训练时长"
            model-value="0"
          ></v-text-field>
        </v-col>

        <v-col cols="12" md="2">
          <v-select
            :items="trainingTime"
            color="primary"
            density="compact"
            variant="outlined"
            label="实际开始训练时间"
          ></v-select>
        </v-col>
     </v-row>

        <v-spacer height="0"></v-spacer>

        <v-row class="pa-1">
        <v-col cols="12" md="2">
          <v-select
            :items="rate"
            color="primary"
            density="compact"
            variant="outlined"
            label="频次（次/周）"
          ></v-select>
        </v-col>
        <v-col cols="12" md="2">
          <v-select
            :items="memberName"
            color="primary"
            density="compact"
            variant="outlined"
            label="介绍会员姓名"
          ></v-select>
        </v-col>

        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="老会员赠送时长"
            model-value="0"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="新会员赠送时长"
            model-value="0"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="2">
          <v-select
            :items="giftType"
            color="primary"
            density="compact"
            variant="outlined"
            label="赠送类型"
          ></v-select>
        </v-col>
        <v-col cols="12" md="2">
          <v-text-field
            variant="outlined"
            color="primary"
            density="compact"
            label="介绍员工姓名"
          ></v-text-field>
        </v-col>
      </v-row>
<!--最后-->
        <v-spacer height="0"></v-spacer>
    <v-row class="pa-1">
     <v-col cols="12" md="6">
      <v-textarea
        variant="outlined"
        color="primary"
        density="compact"
        label="备注："
        >
      </v-textarea>
     </v-col>
    <v-col cols="12" md="6">
      <v-text-field
        variant="outlined"
        color="primary"
        density="compact"
        label="特批原因："
      ></v-text-field>
    <v-row style="margin-top: 2.25%">
      <v-col cols="12" md="6">
      <v-text-field
        variant="outlined"
        color="primary"
        density="compact"
        label="内部员工姓名："
      ></v-text-field>
      </v-col>
        <v-col cols="12" md="6">
       <v-file-input
         variant="outlined"
         color="primary"
         density="compact"
         label="添加附件"
         ></v-file-input>
       </v-col>
    </v-row>
    </v-col>
  </v-row>
      </v-form>
      </v-card>


    <v-card class="mt-2">
      <v-card-text>
        <v-btn color="primary" class="ml-auto" style="margin-right: 1%">
          选择报名套餐
        </v-btn>
        <v-btn color="primary" class="ml-auto" style="margin-right: 1%">
          查看历次报名套餐
        </v-btn>
        <v-btn color="primary" class="ml-auto">
          查看会员详细档案
        </v-btn>
      </v-card-text>
    </v-card>

    </v-container>

</template>

<script>
export default {
  data() {
    return {
      selectedItem: null,
      apply: [
        '初次报名',
        '二次报名',
        '多次报名'
      ],
      Name: [
        '新名称',
        '旧名称',
      ],
      yes: [
        '是',
        '否',
      ],
      trainingTime: [
        '1个月',
        '2个月',
        '3个月',
        '6个月',
        '1年',
        '1年以上',
      ],
      rate: [
        '1次/周',
        '2次/周',
        '3次/周',
        '4次/周',
        '5次及以上',
      ],
      memberName: [
      '王小明',
      '黄北望',
      '托尼贾',
      '李云',
    ],
      giftType: [
      '赠送单次时长',
      '赠送总时长',
      '赠送总次数',
      '赠送体验次数',
    ],
    };
  },

};
</script>

