<template>
    <div id="main">
        <br><br>
            <h2>Basic Information</h2><br>
            <label>First Name:</label> 
            <input v-model='employee.firstName'><br>
            <label>Last Name:</label> 
            <input v-model='employee.lastName'><br>
            <label>Contact Email:</label> 
            <input v-model='employee.contactEmail'><br>
            <label>Company Email:</label> 
            <input v-model='employee.companyEmail'><br>
            <label>Birthday:</label> 
            <input v-model='employee.birthDate'><br>
            <label>Date Hired:</label>
            <input v-model='employee.hiredDate'><br>           
            <button v-on:click='addEmployee'>Next</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            employee: {
                firstName: '',
                lastName: '',
                contactEmail: '',
                companyEmail: '',
                birthDate: '',
                hiredDate: ''
            }
        };
    },
    methods: {
        addEmployee() {
            fetch('http://localhost:8080/Perficient/employees',
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                },
                body: JSON.stringify(this.employee)
            })
            .catch(
                (err) => {console.error(err)}
            )
            this.$router.push({ name: 'add-address' });
        }, 
    }
}
</script>

<style>

</style>