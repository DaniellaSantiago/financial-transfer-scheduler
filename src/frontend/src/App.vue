<template>
  <div id="app" class="main">
      <h1>Transfer Scheduling</h1>
      <TransferStatement />
  </div>
</template>

<script>
import TransferStatement from "./components/TransferStatement.vue";

export default {
  name: 'App',
  components: {
    TransferStatement
  }
}
</script>

<style>
  body {
    margin: 0;
  }

  .main {
    display: flex;
    flex-direction: column;
    height: 100vh;
    padding: 0 20px 40px 20px;
    box-sizing: border-box
  }
</style>
