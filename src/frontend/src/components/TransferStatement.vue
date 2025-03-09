<template>
  <div class="statement-container">
    <div class="statement-header">
        <h2>Transfer Statement</h2>
        <button @click="showTransferForm=true">Schedule Transfer</button>
    </div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Origin Account</th>
          <th>Destination Account</th>
          <th>Value (R$)</th>
          <th>Tax (R$)</th>
          <th>Schedule date</th>
          <th>Transfer Date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="transf in transfers" :key="transf.id">
          <td>{{ transf.id }}</td>
          <td>{{ transf.accountOrigin }}</td>
          <td>{{ transf.accountDestination }}</td>
          <td>{{ transf.value?.toFixed(2) || '0.00' }}</td>
          <td>{{ transf.tax?.toFixed(2) || '0.00' }}</td>
          <td>{{ formatDate(transf.dateScheduling) }}</td>
          <td>{{ formatDate(transf.dateTransference) }}</td>
        </tr>
      </tbody>
    </table>
    <p v-if="transfers.length === 0" class="message">No transfers found.</p>
  </div>

  <TransferForm v-if="showTransferForm" @cancel="showTransferForm=false" @saved="handleSave"/>
</template>

<script>
import TransferForm from "./TransferForm.vue";

export default {
  components: {
    TransferForm
  },
  data() {
    return {
      transfers: [],
      showTransferForm: false,
    };
  },
  methods: {
    async loadTransfers() {
      try {
        const response = await fetch("/api/transfers");

        if (!response.ok) {
            throw new Error("Failed to fetch transfers");
        }

        const data = await response.json();
        this.transfers = data.content;
      } catch (error) {
        console.error("Error loading transfers", error);
      }
    },
    formatDate(data) {
      const dateObj = new Date(data);
      return isNaN(dateObj) ? "Invalid date" : dateObj.toLocaleDateString("pt-BR");
    },
    handleSave() {
      this.showTransferForm=false;
      this.loadTransfers();
    }
  },
  mounted() {
    this.loadTransfers();
  }
};
</script>

<style scoped>
.statement-container {
  max-width: 800px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #f9f9f9;
}

.statement-header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 8px;
  border: 1px solid #ddd;
  text-align: center;
}

th {
  background-color: #f1f1f1;
}

.message {
  margin-top: 10px;
  font-weight: bold;
  text-align: center;
}
</style>
