<template>
  <div id="main">
      <br><br>
      <h2>Update Address</h2>
      <br>
        <label>Street:</label> 
        <input v-model="address.street"><br>
        <label>Suite:</label> 
        <input v-model="address.suite"><br>
        <label>City:</label> 
        <input v-model="address.city"><br>
        <label>Region:</label> 
        <input v-model="address.region"><br>
        <label>Postal:</label> 
        <input v-model="address.postal"><br>
        <label>Country:</label> 
        <input v-model="address.country">
        <button v-on:click="updateAddress(employeeId)">Save</button>
        <button v-on:click="goBack(employeeId)">Cancel</button>
        <button v-on:click="deleteAddress(employeeId)" class="delete">Delete</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            employeeId: '',
            address: {},
        }
    },
    methods: {
        getAddress(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/address`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (address) => {
                    this.address = address;
                }
            )
            .catch (
                (err) => {
                    console.error(err)
                }
            )
        },
        updateAddress(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/address`,
                {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.address)
            })
            .then(
                window.alert('Address Updated')
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.goBack(employeeId)
        },
        deleteAddress(employeeId) {
           fetch(`http://localhost:8080/Perficient/employees/${employeeId}/address`, 
                {
                    method: 'DELETE'
                })
            .then(
                window.alert("Address Deleted")
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.goBack(employeeId)
        },
        goBack(employeeId) {
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        }
    },
    created() {
        this.employeeId = this.$route.params.employeeId;
        this.getAddress(this.$route.params.employeeId);
    }
}
</script>

<style>

</style>