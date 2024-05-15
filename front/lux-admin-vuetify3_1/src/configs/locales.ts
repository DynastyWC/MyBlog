import enMessages from "../locales/en";

const supported = ["en"];
let locale = "en";

try {
  // get browser default language
  const {0: browserLang} = navigator.language.split("-");

  if (supported.includes(browserLang)) locale = browserLang;
} catch (e) {
  console.log(e);
}

export default {
  locale,

  fallbackLocale: "cn",
  availableLocales: [
    {
      code: "en",
      flag: "us",
      name: "united-states",
      label: "中文",
      messages: enMessages,
    }
  ],
  messages: {
    en: enMessages
  },
};
