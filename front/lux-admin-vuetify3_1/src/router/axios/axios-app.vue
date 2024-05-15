<template>
  <div style="margin-left: 5%">app测试</div>
  <br>
  <v-btn @click="sendPostRequest" style="margin-right: 2.5%">Send POST Request</v-btn>
  <v-btn @click="sendPutRequest" style="margin-right: 2.5%">Send PUT Request</v-btn>
  <v-btn @click="sendGetRequest">Send GET Request</v-btn>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import {postRequest, putRequest, getRequest} from './apiRequests'; // 导入 getRequest 函数

// 修改IP
// const config = {
//   ip: 'http://' + window.location.hostname.toString(),
//   port: 9601
// };
// const Url = `${config.ip}:${config.port}/emp/1692439165452541954`;
const Url = '/api';
console.log('URL:', Url);
export default defineComponent({
  methods: {
    async sendPostRequest() {
      const postData: {} = {
        staffGender: 1,
      };
      try {
        const response = await postRequest(Url, postData);
        console.log('POST Response:', response.data);
      } catch (error) {
        console.error('POST Error:', error);
      }
    },

    async sendGetRequest() {

      try {
        const response = await getRequest(Url);
        console.log('GET Response:', response.data);
      } catch (error) {
        console.error('GET Error:', error);
      }
    }
  }
});
</script>
