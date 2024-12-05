<template>
  <div class="d-flex text-center flex-grow-1">
    <v-sheet
      class="layout-side d-none d-md-flex flex-column justify-space-between"
    >
      <div class="mt-3 mt-md-10 pa-2">
        <div class="text-primary text-h4 font-weight-bold">
          KidsAttentionProjects
          2023
        </div>
        <div class="title my-2">
          欢迎来到儿童专注力训练系统
        </div>
      </div>
    </v-sheet>
    <v-card
      class="flex-grow-1 d-flex align-center justify-center flex-column"
      variant="outlined"
    >
      <div class="layout-content ma-auto w-full">
        <slot></slot>
      </div>
      <div class="pa-5">https://github.com/CCP101/KidsAttentionProject2023</div>
    </v-card>
  </div>
</template>

<style lang="scss" scoped>
.layout-side {
  width: 420px;
}

.layout-content {
  max-width: 480px;
}
</style>
