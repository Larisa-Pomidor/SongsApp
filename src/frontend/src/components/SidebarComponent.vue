<template>
  <div class="sidebar">
    <div class="sidebar__outer">
      <div class="container">
        <div class="sidebar__inner">
          <div class="sidebar__search">
            <div class="sidebar__search-block">
              <input type="text" class="sidebar__search-input" placeholder="Search" v-on:keyup.enter="search">
              <div class="sidebar__seach-button" @click="search">
                <img src="@/assets/img/search-icon.png">
              </div>
              <div class="sidebar__mix-button" @click="mix">
                <img src="@/assets/img/mix-icon.png">
              </div>
            </div>
          </div>
          <div class="sidebar__tags">

          </div>
          <div class="sidebar__song-list sidebar__song-list_start">
            <div class="sidebar__song-item" v-for="(song, i) in songs" :key="i" @click="chooseSong(song.id, $event)">
              <div class="sidebar__song-name">
                {{ song.name }}
              </div>
              <div class="sidebar__song-author">
                {{ song.author }}
              </div>
              <div class="sidebar__song-delete" @click="remove(song.id)" v-if=adminSidebar>
                <img src="@/assets/img/delete-icon.png" alt="">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'SidebarComponent',
  props: ["chooseSong", "gettext", "mix", 'songs', 'search', 'adminSidebar', 'getCookie'],
  methods: {
    remove(id) {
      axios.delete('/api/v1/songs/' + id, {
        headers: {
          "Authorization": "Bearer " + this.getCookie("access_token")
        }
      });
    }
  }
}

</script>
