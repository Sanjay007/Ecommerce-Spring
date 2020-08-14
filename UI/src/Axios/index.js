import axios from 'axios'

// Create instance called instance
const axiosInst = axios.create({
    baseURL: 'http://localhost:8080'
});
axiosInst.defaults.headers.post['Content-Type'] = 'application/json';

axiosInst.interceptors.request.use(request => {
    console.log(request);
    // Edit request config
    return request;
}, error => {
    console.log(error);
    return Promise.reject(error);
});

axiosInst.interceptors.response.use(response => {
    console.log(response);
    // Edit response config
    return response;
}, error => {
    console.log(error);
    return Promise.reject(error);
});

let token = "";
export const tokenValue = token;

export default {
    login: (request, history) =>
         axiosInst.post('/api/auth/signin', request),
            
    addProduct: (request,history) =>
    
        axiosInst({
            'method': 'POST',
            'url': '/api/product',
            'data': request,
            'headers':{"Authorization":"Bearer "+localStorage.getItem('user')}
        }),

}