<template>
  <div id="main">
      <br><br>
        <h2>Address</h2><br>
        <label>Street:</label>  
        <input v-model='address.street'><br>
        <label>Suite:</label> 
        <input v-model='address.suite'><br>
        <label>City:</label> 
        <input v-model='address.city'><br>
        <label>Region:</label>  
        <input v-model='address.region'><br>
        <label>Postal:</label> 
        <input v-model='address.postal'><br>
        <label>Country:</label> 
        <input v-model='address.country' placeholder="2 Letter Country Code"><br>
        <button v-on:click='setAddressEmployeeId'>Next</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            newEmployee: {},
            address: {
                employeeId: '',
                street: '',
                suite: '',
                city: '',
                region: '',
                postal: '',
                country: ''
            }
        }
    },
    methods: {
        setAddressEmployeeId() {
            this.address.employeeId = this.newEmployee.employeeId;
            this.addAddress(this.newEmployee.employeeId);
        },
        setNewEmployee() {
            fetch(`http://localhost:8080/Perficient/employees/new`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (newEmployee) => {
                    this.newEmployee = newEmployee;
                }
            )
            .catch (
                (err) => {
                    console.error(err);
                }
            )
        },
        addAddress(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/address`,
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                },
                body: JSON.stringify(this.address)
            })
            .catch(
                (err) => {
                    console.error(err)
                }
           )
            this.$router.push({ name: 'add-role' });
        }
    },
    created() {
    this.setNewEmployee();
    }
}
</script>

<style>

</style>