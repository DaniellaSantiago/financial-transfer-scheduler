<template>
  <div id="app">
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
