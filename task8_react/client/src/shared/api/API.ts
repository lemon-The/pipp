import Axios, { AxiosError } from 'axios';

export const axios = Axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: true,
});

export function isAxiosError<ResponseType>(error: unknown): error is AxiosError<ResponseType> {
  return Axios.isAxiosError(error);
}

axios.interceptors.response.use(
  (response) => response,
  (error: AxiosError) => {
    return Promise.reject(error.response?.data || { message: 'Неизвестная ошибка' });
  },
);
