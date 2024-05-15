<script lang="ts" setup>
import {useAuthStore} from "@/stores/authStore";

const authStore = useAuthStore();
const isLoading = ref(false);
const isSignInDisabled = ref(false);

const refLoginForm = ref();
const email = ref("admin");
const password = ref("admin");
const isFormValid = ref(true);

// show password field
const showPassword = ref(false);

const handleLogin = async () => {
  const {valid} = await refLoginForm.value.validate();
  if (valid) {
    isLoading.value = true;
    isSignInDisabled.value = true;
    authStore.loginWithEmailAndPassword(email.value, password.value);
  } else {
    console.log("no");
  }
};

// const signInWithGoolgle = () => {
//   authStore.loginWithGoogle();
// };

// Error Check
const emailRules = ref([
  (v: string) => !!v || "需要您输入账户",
  // (v: string) => /.+@.+\..+/.test(v) || "E-mail must be valid",
]);

const passwordRules = ref([
  (v: string) => !!v || "需要您输入密码",
  (v: string) =>
    (v && v.length <= 10) || "密码最多10个字符",
]);

// error provider
const errorProvider = ref(false);
const errorProviderMessages = ref("");

const error = ref(false);
const errorMessages = ref("");
const resetErrors = () => {
  error.value = false;
  errorMessages.value = "";
};

const signInWithFacebook = () => {
  alert(authStore.isLoggedIn);
};
</script>
<template>
  <v-card class="pa-3 ma-3" color="white" elevation="3">
    <v-card-title class="my-4 text-h4">
      <span class="flex-fill"> 欢迎 </span>
    </v-card-title>
    <v-card-subtitle>请登录您的账户</v-card-subtitle>
    <!-- 登录界面 -->

    <v-card-text>
      <v-form
        ref="refLoginForm"
        v-model="isFormValid"
        class="text-left"
        lazy-validation
      >
        <v-text-field
          ref="refEmail"
          v-model="email"
          :error="error"
          :label="$t('login.email')"
          :rules="emailRules"
          bg-color="#fff"
          color="primary"
          density="default"
          hint="请输入账户以此来访问系统"
          name="账号"
          outlined
          placeholder="请输入您的账户"
          required
          validateOn="blur"
          variant="underlined"
          @change="resetErrors"
          @keyup.enter="handleLogin"
        ></v-text-field>
        <v-text-field
          ref="refPassword"
          v-model="password"
          :append-inner-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          :error="error"
          :error-messages="errorMessages"
          :label="$t('login.password')"
          :rules="passwordRules"
          :type="showPassword ? 'text' : 'password'"
          bg-color="#fff"
          color="primary"
          density="default"
          hint="请输入密码以此来访问系统"
          name="password"
          outlined
          placeholder="密码"
          validateOn="blur"
          variant="underlined"
          @change="resetErrors"
          @keyup.enter="handleLogin"
          @click:append-inner="showPassword = !showPassword"
        ></v-text-field>
        <v-btn
          :disabled="isSignInDisabled"
          :loading="isLoading"
          block
          class="mt-2"
          color="primary"
          size="x-large"
          @click="handleLogin"
        >{{ $t("login.button") }}
        </v-btn
        >


        <div v-if="errorProvider" class="error--text my-2">
          {{ errorProviderMessages }}
        </div>

        <div class="mt-5 text-center">
          <router-link class="text-primary" to="/auth/forgot-password">
            {{ $t("login.forgot") }}
          </router-link>
        </div>
      </v-form>
    </v-card-text
    >
  </v-card>

</template>
