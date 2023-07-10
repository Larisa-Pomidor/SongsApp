<template>
  <div class="admin">
    <div class="admin__outer">
      <div class="container">
        <div class="admin__inner">
          <div class="admin__control-buttons">
            <div class="admin__create-button" @click="toggleNew">
              New +
            </div>
            <div class="admin__update-button" @click="toggleUpdate">
              Update
            </div>
          </div>
          <div class="admin__nform-block" v-if=newShow>
            <div class="admin__form-title">
              Add new song
            </div>
            <form class="admin__form" action="">
              <input type="text" v-model="name" placeholder="Song name">
              <input type="text" v-model="author" placeholder="Song author">
              <input type="keys" v-model="keys" placeholder="Song keys">
              <input type="file" ref="file" @change="handleFileUpload()">
              <div class="admin__form-button" @click=addNewSong()>
                Add
              </div>
            </form>
          </div>
          <div class="admin__uform" v-if=updateShow>
            <div class="admin__form-title">
              Update song
            </div>
            <form action="">
              <input type="text" v-model="name" :placeholder=active_song.name>
              <input type="text" v-model="author" :placeholder=active_song.author>
              <div class="admin__form-button" @click=updateSong()>
                Update
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <Sidebar :adminSidebar=adminSidebar :songs=songs :chooseSong=chooseSong :getCookie=getCookie />
</template>
<script>
import axios from "axios";
import Sidebar from '@/components/SidebarComponent.vue'

export default {
  components: {
    Sidebar
  },
  name: 'SongView',
  data() {
    return {
      newShow: true,
      updateShow: false,
      name: '',
      author: '',
      file: '',
      keys: '',
      songs: '',
      adminSidebar: true,
      active_song: ''
    }
  },
  methods: {
    toggleNew() {
      this.newShow = !this.newShow;
      this.updateShow = false;
    },
    toggleUpdate() {
      this.updateShow = !this.updateShow;
      this.newShow = false;
    },
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    },
    addNewSong() {
      let formData = new FormData();
      formData.append('file', this.file);
      axios.post('/api/v1/songs/add?name=' + this.name + "&author=" + this.author + "&keys=dr",
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
      ).then(function () {
        this.name = "";
        this.author = "";
        this.file = "";
        this.keys = "";
      });
    },
    songList() {
      fetch("/api/v1/songs/", {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
        .then((response) => response.json())
        .then((data) => {
          this.songs = data;
          this.active_song = data[0];
        })
    },
    chooseSong(i, element) {
      fetch("/api/v1/songs/" + i, {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
        .then((response) => response.json())
        .then((data) => {
          this.active_song = data;
          // if (data.texts.length > 0) {
          //   this.getText(data.texts[0].content);
          // }
          // else {
          //   this.current_text = '';
          // }
        });

      let start_list = document.querySelector(".sidebar__song-list_start");
      if (start_list != null)
        document.querySelector(".sidebar__song-list_start").classList.remove("sidebar__song-list_start");

      let active_song = document.querySelector(".sidebar__song-item_active");
      if (active_song != null) {
        document.querySelector(".sidebar__song-item_active").classList.remove("sidebar__song-item_active");
      }

      if (element.target.parentElement.classList.contains("sidebar__song-item")) {
        element.target.parentElement.classList.add("sidebar__song-item_active");
      }
      else {
        element.target.classList.add("sidebar__song-item_active");
      }
    }
  },
  props: ["getCookie"],
  mounted() {
    this.songList();
  }
}
</script>
