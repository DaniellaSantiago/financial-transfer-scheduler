<template>
  <div class="statement-container">
    <div class="statement-header">
        <h2>Transfer Statement</h2>
        <button @click="showTransferForm=true">Schedule Transfer</button>
    </div>
    <div class="table-container">
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
    </div>
    <p v-if="transfers.length === 0" class="message">No transfers found.</p>

    <div v-if="transfers.length" class="pagination">
      <label for="pageSize">Items per page:</label>
      <select id="pageSize" v-model="pageSize" @change="goToPage(0)">
        <option v-for="size in availablePageSizes" :key="size" :value="size">
          {{ size }}
        </option>
      </select>

      <button @click="goToPage(0)" :disabled="page === 0">«</button>
      <button @click="goToPage(page - 1)" :disabled="page === 0">‹</button>

      <span>{{ page + 1 }} of {{ totalPages }}</span>

      <button @click="goToPage(page + 1)" :disabled="page + 1 === totalPages">›</button>
      <button @click="goToPage(totalPages - 1)" :disabled="page + 1 === totalPages">»</button>
    </div>
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
      page: 0,
      pageSize: 20,
      totalPages: 1,
      availablePageSizes: [5, 10, 20, 50, 100]
    };
  },
  methods: {
    async loadTransfers() {
      try {
        const response = await fetch(`/api/transfers?page=${this.page}&size=${this.pageSize}`);

        if (!response.ok) {
            throw new Error("Failed to fetch transfers");
        }

        const data = await response.json();
        this.transfers = data.content;
        this.totalPages = data.totalPages;
      } catch (error) {
        console.error("Error loading transfers", error);
      }
    },
    formatDate(data) {
      const dateObj = new Date(data);
      return isNaN(dateObj) ? "Invalid date" : dateObj.toLocaleDateString("pt-BR");
    },
    handleSave() {
      this.showTransferForm = false;
      this.loadTransfers();
    },
    goToPage(page) {
      this.page = page;
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
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #f9f9f9;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.statement-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.pagination {
  margin-top: 10px;
  display: flex;
  gap: 4px;
  justify-content: flex-end;
}

.table-container {
  overflow: auto;
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
  position: sticky;
  top: 0;
}

.message {
  margin-top: 10px;
  font-weight: bold;
  text-align: center;
}
</style>
