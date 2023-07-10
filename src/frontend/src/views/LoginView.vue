<template>
    <div class="login">
        <div class="login__outer">
            <div class="container">
                <div class="login__inner">
                    <div class="login__title">
                        Please, log in.
                    </div>
                    <div class="login__form">
                        <input type="text" v-model=username class="login__input" placeholder="username">
                        <input type="password" v-model=password class="login__input" placeholder="password">
                        <button @click="login()">Log in</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {

        login() {
            var params = new URLSearchParams();
            params.append('email', this.username);
            params.append('password', this.password);

            axios.post('/api/v1/registration/login', {
                "email": this.username,
                "password": this.password
            },
                {
                    headers: { "Content-type": "application/json" }
                }
            ).then((data) => {
                this.set_cookie("access_token", data.data);
                this.toggleLogged();
            })
        },
        getCookie(name) {
            var value = "; " + document.cookie;
            var parts = value.split("; " + name + "=");
            if (parts.length == 2) return parts.pop().split(";").shift();
        },
        set_cookie(name, value) {
            document.cookie = name + '=' + value + '; Path=/;';
        },
        delete_cookie(name) {
            document.cookie = name + '=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
        }
    },
    props: ["toggleLogged"]
}
</script>
