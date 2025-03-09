<template>
  <div class="background"></div>
  <div class="form-container">
    <h2>Schedule Transfer</h2>
    <form @submit.prevent="scheduleTransfer">
      <div class="form-group">
        <label>Origin Account:</label>
        <input v-model="transfer.accountOrigin" placeholder="XXXXXXXXXX" required />
      </div>

      <div class="form-group">
        <label>Destination Account:</label>
        <input v-model="transfer.accountDestination" placeholder="XXXXXXXXXX" required />
      </div>

      <div class="form-group">
        <label>Valor (R$):</label>
        <input v-model.number="transfer.value" type="number" placeholder="0.00" required />
      </div>

      <div class="form-group">
        <label>Scheduling Date:</label>
        <input v-model="transfer.dateScheduling" type="date" readonly />
      </div>

      <div class="form-group">
        <label>Transfer Date:</label>
        <input v-model="transfer.dateTransference" type="date" required />
      </div>

      <div class="button-container">
        <button @click="cancel" class="btn btn-secondary">Cancel</button>
        <button type="submit" class="btn">To schedule</button>
      </div>
    </form>
    <p v-if="message" class="message">{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      transfer: {
        accountOrigin: "",
        accountDestination: "",
        value: 0,
        dateScheduling: new Date().toISOString().split("T")[0],
        dateTransference: "",
      },
      message: "",
    };
  },
  methods: {
    async scheduleTransfer() {
        try {
            const response = await fetch("/api/transfers", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.transfer),
            });

            if (!response.ok) {
               throw new Error("Failed to schedule transfer");
            }

            this.message = "Transfer scheduled successfully!";
            this.$emit("saved")
            this.clearForm();
        } catch (error) {
            this.message = "Error scheduling transfer. Please check the data.";
        }
    },
    clearForm() {
        this.transfer = {
            accountOrigin: "",
            accountDestination: "",
            value: 0,
            dateScheduling: new Date().toISOString().split("T")[0],
            dateTransference: "",
        };
    },
    cancel() {
        this.$emit("cancel")
    },
  },
};
</script>

<style scoped>
.background {
  position: fixed;
  width: 100vw;
  height: 100vh;
  top: 0;
  left: 0;
  background: rgb(100 100 100 / 80%);
}
.form-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #f9f9f9;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

input[readonly] {
  background: #c6c6c6
}

.button-container {
  display: flex;
  gap: 12px;
}

.btn {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.btn:hover {
  background-color: #218838;
}

.btn-secondary {
  background-color: #535854;
}

.btn-secondary:hover {
  background-color: #3e423f
}

.mensagem {
  margin-top: 10px;
  font-weight: bold;
  text-align: center;
}
</style>
