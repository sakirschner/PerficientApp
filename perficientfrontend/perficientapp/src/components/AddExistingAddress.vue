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
        <button v-on:click='setAddressEmployeeId'>Save</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            employee: {},
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
            this.address.employeeId = this.employee.employeeId;
            this.addAddress(this.employee.employeeId);
        },
        setEmployee(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (employee) => {
                    this.employee = employee;
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
            .then(
                window.alert("Address Added")
            )
            .catch(
                (err) => {
                    console.error(err)
                }
           )
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        }
    },
    created() {
    this.setEmployee(this.$route.params.employeeId);
    }
}
</script>

<style>

</style>