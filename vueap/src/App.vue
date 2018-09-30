<template>
  <div id="app">
    <ct-input></ct-input>
  </div>
</template>

<script>
import ctInput from './components/CtInput'

export default {
  name: 'App',
  components: {
    ctInput
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
