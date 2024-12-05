<template>

      <iframe :src="pdfUrl" width="100%" height="1000vh" style="background-color:#f0f0f0 " title="PDF Viewer"></iframe>

</template>

<script>


export default {
  data() {
    return {
      pdfUrl: '/KAP.pdf', // 替换成你的PDF链接
    };
  },
  components: {
    // 如果使用vue-pdf库，请将组件添加到这里
    // PdfViewer,
  },
};
</script>

<style>

</style>
