import http from '@http/http'
// 用户列表
export const userList = (obj) => http.GET('/api/book/list', obj)
// 用户登陆
export const user = (obj) => http.GET('/api/user/login', obj)
// 用户注册
export const regUser = (obj) => http.GET('/api/user/register', obj)
// 搜索酒店
export const searchHotel = (obj) => http.GET('/api/hotel/search', obj)
// 发送邮箱验证码
export const sendEmail = (obj) => http.GET('/api/user/sendEmail', obj)
// 验证码是否正确
export const verifyCode = (obj) => http.GET('/api/user/verify', obj)
// 获取酒店详情
export const getDetail = (obj) => http.GET('/api/user/verify', obj)
