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
// export const verifyCode = (obj) => http.GET('/api/user/verify', obj)
// 获取酒店详情
export const getDetail = (obj) => http.GET('/api/hotel/detail', obj)
// 每个页面都获取用户信息
export const getUserInfo = () => http.GET('/api/user/detail')
// 获取上传到后端的头像，上传到数据库
export const getTempPhoto = () => http.GET('/api/file/yesurl')
// 用户下单
export const reserve = (obj) => http.GET('/api/userorder/placeOrder', obj)
// 下单确定写到数据库中
export const confirmOrder = (obj) => http.GET('/api/userorder/submitOrder', obj)
// 用户获取自己的所有订单
export const getMyAllOrder = () => http.GET('/api/userorder/orderdetail')
// 用户评价
export const writeComment = (obj) => http.GET('/api/userorder/updatecomment', obj)
// 修改密码
export const changePassword = (obj) => http.GET('/api/user/newpassword', obj)
// 忘记密码要发送验证码
export const sendEmailForPassword = (obj) => http.GET('/api/user/sendMailForPassword', obj)
// reset密码
export const resetPwd = (obj) => http.GET('/api/user/forgetPassword', obj)
// 管理员获取酒店列表
export const adminGetHotelList = () => http.GET('/api/hotel/hotellist')
// 管理员增加酒店
export const addHotel = (obj) => http.POST('/api/user/addHotel', obj)
// 管理员获取酒店详情
export const adminGetDetail = (obj) => http.GET('/api/userorder/hoteldetail', obj)
// 管理员获取用户列表
export const adminGetUserList = () => http.GET('/api/user/alluser')
// 管理员删除指定用户
export const adminDelUser = (obj) => http.GET('/api/user/alluser', obj)
// 管理员删除指定酒店
export const adminDelHotel = (obj) => http.GET('/api/user/alluser', obj)
// 管理员获取订单列表
export const adminGetOrderList = () => http.GET('/api/userorder/allorder')
// 管理员修改指定用户的密码
export const adminChangeUserPwd = (obj) => http.GET('/api/user/newpass', obj)
// 管理员修改酒店某个room的价格
export const adminChangePrice = (obj) => http.GET('/api/user/alluser', obj)
