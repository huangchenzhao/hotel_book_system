<template>
<!--    <div class="header">-->
<!--        <img src="../assets/images/logo.png" alt="logo" />-->
<!--        <h1>微旅——您身边的酒店预订系统</h1>-->
<!--    </div>-->
    <div id="logo-wrap">
        <div id="logo-bar">
            {{logo}}
            <h1 class="title">微旅——您身边的酒店预订系统</h1>
        </div>
    </div>
</template>

<script>
export default {
  name: 'Header'
}
</script>

<style scoped>
    /*.header{*/
    /*    margin-top: 0;*/
    /*}*/
    /*.header img {*/
    /*    float: left;*/
    /*    width: 150px;*/
    /*    height: 100px;*/
    /*    background: #555;*/
    /*}*/

    /*.header h1 {*/
    /*    position: relative;*/
    /*    top: 18px;*/
    /*    left: 10px;*/
    /*}*/
    #logo-wrap {
        background: url('../assets/images/header.png');
        background-size: cover;
    }
    #logo-bar {
        height: 200px !important;
        img {
            margin: 55px 0 0 25px;
            display: block;
        }
    }
    .title{
        margin-top: 0;
        text-align: center;
        padding-top: 60px;
        color: #dcdfe6;
        font-family:Arial,Helvetica,sans-serif;
    }
</style>
