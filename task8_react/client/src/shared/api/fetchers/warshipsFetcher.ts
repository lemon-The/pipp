import { axios } from '../API.ts';
import { Warship } from '../../types/types.ts';

export const getWarships = () => axios.get<Warship[]>('/warships');

export const createWarship = (data: any) => axios.post<Warship>('/warships/save', data);
