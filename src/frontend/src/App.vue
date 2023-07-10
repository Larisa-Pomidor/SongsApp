<template>
  <div class="header">
    <div class="header__outer">
      <div class="header__menu">
        <div class="header__greet">
          {{ greeting }}
        </div>
        <router-link to="/">Songs</router-link>
        <router-link to="/admin">Admin</router-link>
        <router-link v-if=!loggedIn to="/login">Login</router-link>
        <div v-if=loggedIn class="header__logout" @click=LogOut>
          Logout
        </div>
      </div>
    </div>
  </div>
  <router-view :getCookie=getCookie :toggleLogged=toggleLogged />
</template>
<script>

export default {
  data() {
    return {
      loggedIn: false,
      greeting: ''
    }
  },
  methods: {
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
    },
    LogOut() {
      this.delete_cookie("access_token");
      this.loggedIn = false;
      window.location.href = "/login";
    },
    toggleLogged() {
      this.loggedIn = !this.loggedIn;
    }
  },
  mounted() {
    if (this.getCookie("access_token")) {
      // axios.get("/getName?access_token=" + this.getCookie("access_token"))
      //   .then(function (response) {
      //     this.greeting = "Let's dance, " + response.data;
      //     this.loggedIn = true;
      // if (window.location.pathname != "/login")
      //        window.location.href = "/song";
      //   }.bind(this))
      //   .catch(function () {
      //     this.delete_cookie("access_token");
      //     this.loggedIn = false;
      //     if (window.location.pathname != "/login")
      //       window.location.href = "/login";
      //   }).bind(this);
    }
    else {
      if (window.location.pathname != "/login")
             window.location.href = "/login";
    }
  }
}
</script>
