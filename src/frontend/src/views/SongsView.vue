<template>
  <div class="song">
    <div class="song__outer">
      <div class="container">
        <div class="song__inner">
          <div class="song__block">
            <div class="song__info">
              <div class="song__name">
                {{ active_song.name }}
              </div>
              <div class="song__author">
                {{ active_song.author }}
              </div>
              <div class="song__tabs">
                <div class="song__tab-block" v-for="(text, i) in active_song.texts" :key="text.id">
                  <div class="song__tab-item" @click="getText(text.content)">
                    #{{ i + 1 }} - {{ text.keys }}
                  </div>
                </div>
              </div>
              <div class="songs__text">
                <div class="songs__text-line" v-for="(line, i) in current_text" v-html="line" :key="i">
                </div>
              </div>
            </div>
            <div class="song__chords">
              <div class="song__chords-inner">
                <div class="song__chord-item" v-for="(chord, i) in active_song.chords" :key="i">
                  <div class="song__chord-image"><img :src="require('@/assets/img/chords/' + chord.image + '')" alt="">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <Sidebar :chooseSong=chooseSong :gettext=getText :mix=mixSongs :search=searchSongs :songs=songs :adminSidebar=adminSidebar />
</template>

<script>
import Sidebar from '@/components/SidebarComponent.vue'

export default {
  components: {
    Sidebar
  },
  data() {
    return {
      active_song: '',
      current_text: '',
      songs: '',
      showSidebar: true,
      adminSidebar: false
    }
  },
  methods: {
    chooseSong(i, element) {
      fetch("/api/v1/songs/" + i, {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
        .then((response) => response.json())
        .then((data) => {
          this.active_song = data;
          if (data.texts.length > 0) {
            this.getText(data.texts[0].content);
          }
          else {
            this.current_text = '';
          }
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
    },
    songList() {
      fetch("/api/v1/songs/", {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
        .then((response) => response.json())
        .then((data) => {
          this.activateFirst(data);
        })
    },
    getText(path) {
      fetch("/api/v1/texts/" + path, {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
        .then((response) => response.json())
        .then((data) => {
          this.current_text = data;
        })
    },
    mixSongs() {
      fetch("/api/v1/songs/mix", {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
        .then((response) => response.json())
        .then((data) => {
          this.activateFirst(data);

          let active_song = document.querySelector(".sidebar__song-item_active");
          if (active_song != null) {
            document.querySelector(".sidebar__song-item_active").classList.remove("sidebar__song-item_active");
          }
          document.querySelector(".sidebar__song-item:nth-child(1)").classList.add("sidebar__song-item_active");
        })
    },
    searchSongs() {
      let search = document.querySelector(".sidebar__search-input").value;
      fetch("/api/v1/songs/search?like=" + search, {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
        .then((response) => response.json())
        .then((data) => {
          this.activateFirst(data);
        })
    },
    activateFirst(data) {
      this.songs = data;
      this.active_song = data[0];
      if (data[0].texts.length > 0) {
        this.getText(data[0].texts[0].content);
      }
      else this.current_text = '';
    }
  },
  mounted() {
    this.songList();

    fetch("/api/v1/songs/short", {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      })
      .then((response) => response.json())
      .then((data) => {
        this.songs = data;
      })
  },
  props: ["getCookie"]
}
</script>