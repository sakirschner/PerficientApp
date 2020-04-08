<template>
    <div id="main">
        <br><br>
        <div class="employees">
            <input type="text" v-model="search" placeholder="Search By First Name.."/>
            <div v-for='employee in filteredList' :key='employee.employeeId' class="card">
            <br>
            <h2>{{employee.firstName}}  {{employee.lastName}}</h2>
            <p>Employee Id: {{employee.employeeId}}</p>
            <p>Contact Email: {{employee.contactEmail}}</p>
            <p>Company Email: {{employee.companyEmail}}</p>
            <p>Birthday: {{employee.birthDate}}</p>
            <p>Date Hired: {{employee.hiredDate}}</p>
            <router-link :to="{name: 'employee-details', params: {employeeId: employee.employeeId}}">View Details</router-link>
            <br>
            </div>
        </div>
    </div> 
</template>

<script>
export default {
    data() {
        return {
            search: '',
            employees: []
        }
    },
    computed: {
        filteredList() {
            return this.employees.filter(
                (employee) => {
                    return employee.firstName.toLowerCase().includes(
                        this.search.toLocaleLowerCase()
                    )
                }
            )
        }
    },
    created() {
        fetch('http://localhost:8080/Perficient/employees')
        .then(
            (response) => {
                return response.json();
            }
        )
        .then (
            (employee) => {
                this.employees = employee;
            }
        )    
        .catch (
            (err) => {
                console.error(err);
            }
        )
    }
}
</script>

<style>
.employees{
    margin-top: 20px;
}
</style>
