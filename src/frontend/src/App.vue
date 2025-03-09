<template>
  <div id="app">
      <h1>Transfer Scheduling</h1>
      <TransferForm />
      <hr />
      <TransferStatement />
  </div>
</template>

<script>
import TransferForm from "./components/TransferForm.vue";
import TransferStatement from "./components/TransferStatement.vue";

export default {
  name: 'App',
  components: {
    TransferForm,
    TransferStatement
  }
}
</script>
