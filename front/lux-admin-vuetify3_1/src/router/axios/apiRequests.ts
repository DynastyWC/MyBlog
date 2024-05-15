import axios, {AxiosResponse} from 'axios';

// 定义 POST 请求函数
export function postRequest<T>(url: string, data: any): Promise<AxiosResponse<T>> {
  return axios.post<T>(url, data);
}

// 定义 PUT 请求函数
export function putRequest<T>(url: string, data: any): Promise<AxiosResponse<T>> {
  return axios.put<T>(url, data);
}

// 定义GET请求函数
export function getRequest<T>(url: string): Promise<AxiosResponse<T>> {
  return axios.get(url)
    .then(response => response.data)
    .catch(error => {
      throw error;
    });
}
