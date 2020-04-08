<template>
    <div id="main">
        <br><br>
        <div v-if="!editing">
            <h1>{{employee.firstName}} {{employee.lastName}}</h1>
            <div class="card">
                <br>
                <h2>Basic Information</h2>
                <p>Employee ID: {{employee.employeeId}}</p>
                <p>Contact Email: {{employee.contactEmail}}</p>
                <p>Company Email: {{employee.companyEmail}}</p>
                <p>Birthday: {{employee.birthDate}}</p>
                <p>Date Hired: {{employee.hiredDate}}</p>
                <a v-on:click="enableEditing">Edit Info</a>
                <br>
            </div>

            <div v-if="role.employeeId != null" class="card"> 
                <br>
                <h2>Role</h2>
                <p>Role: {{role.roleName}}</p>
                <p>Business Unit: {{role.businessUnitName}}</p>
                <router-link :to="{name: 'update-role', params: {employeeId: employeeId}}">
                    <p>Edit Role</p>
                </router-link>
            </div>
            
            <div v-if="role.employeeId === null" class="button">
                <router-link :to="{name: 'add-existing-role', params: {employeeId: employeeId}}">
                    <button>Add Role</button>
                </router-link>
            </div>
            <!--Manager Info-->
           <!-- <div v-if="employee.assignment.managerFirstName !=null">
            Manager: 
            <router-link :to="{name: 'employee-details', params: {employeeId: employee.assignment.superiorId}}" target="_blank">
                {{employee.assignment.managerFirstName}} {{employee.assignment.managerLastName}}
            </router-link><br><br>
            <router-link :to="{name: 'update-assignment', params: {employeeId: employeeId}}">
                <button>Edit Manager</button><br><br>
            </router-link>
            </div> -->

            <div v-if="employee.address.street != null" class="card">
                <br>
                <h2>Address</h2>
                <p>Street: {{employee.address.street}}</p>
                <p>Suite: {{employee.address.suite}}</p>
                <p>City: {{employee.address.city}}</p>
                <p>Region: {{employee.address.region}}</p>
                <p>Postal: {{employee.address.postal}}</p>
                <p>Country: {{employee.address.country}}</p>
                <router-link :to="{name: 'update-address', params: {employeeId: employeeId}}">
                    <p>Edit Address</p>
                </router-link> 
            </div>

            <div class="button"  v-if="employee.address.street === null">
                <router-link :to="{name: 'add-existing-address', params: {employeeId: employeeId}}">
                    <button>Add Address</button>
                </router-link>
            </div>

            <div v-for='skill in skills' :key='skill.field.fieldId' class="card">
                <br>
                <h2>Skill</h2>
                <p>Skill: {{skill.field.name}}</p>
                <p>Type: {{skill.field.type}}</p>
                <p>Experince: {{skill.experience}} Months</p>
                <p>Summary: {{skill.summary}}</p>
                <router-link :to="{name: 'update-skill', params: {employeeId: employeeId, skillId: skill.skillId, fieldId:skill.field.fieldId}}">
                    <p>Edit Skill</p>
                </router-link> 
            </div>

            <div class="button">
            <router-link :to="{name: 'add-existing-field', params: {employeeId: employeeId}}">
                <button>Add Skill</button>
            </router-link> 
            </div>
            <button v-if="((skills.length === 0) && 
                (employee.address.street === null) &&
                (role.employeeId === null))" 
                v-on:click="deleteEmployee(employee.employeeId)" class="delete">
                Delete Employee
                </button>
            </div>

        <div v-if="editing">
            <h2>Update Information</h2>
            <br><br>
            <label>First Name:</label>
            <input v-model="editEmployee.firstName" class="md-input" required><br>
            <label>Last Name:</label>
            <input v-model="editEmployee.lastName" class="md-input" required><br>
            <label>Contact Email:</label> 
            <input v-model="editEmployee.contactEmail"><br>
            <label>Company Email:</label>
            <input v-model="editEmployee.companyEmail" required><br>
            <label>Birthday:</label>
            <input v-model="editEmployee.birthDate" required><br>
            <label>Date Hired:</label>
            <input v-model="editEmployee.hiredDate" required><br>
            <button v-on:click="updateEmployee(editEmployee.employeeId)">Save</button>
            <button v-on:click="disableEditing">Cancel</button>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            employeeId: '',
            employee : {},
            editEmployee: null,
            skills : [],
            role: {},
            editing: false,
        }
    },
    methods: {
        getEmployeeDetails(employeeId) {
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
                    console.error(err)
                }
            )
        },
        getSkills(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/skills`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (skills) => {
                    this.skills = skills;
                }
            )
            .catch (
                (err) => {
                    console.error(err)
                }
            )
        },
        getRole(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/role`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (role) => {
                    this.role = role;
                }
            )
            .catch (
                (err) => {
                    console.error(err)
                }
            )
        },
        updateEmployee(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}`,
                {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.editEmployee)
            })
            .then(
                window.alert('Employee Updated')
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.employee = this.editEmployee;
            this.disableEditing();
        },
        deleteEmployee(employeeId) {
           fetch(`http://localhost:8080/Perficient/employees/${employeeId}`, 
                {
                    method: 'DELETE'
                })
            .then(
                window.alert('Employee Deleted')
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.$router.push({name: 'employees'})
        },
        enableEditing() {
            this.editEmployee = this.employee;
            this.editing = true;
        },
        disableEditing() {
            this.editEmployee = null;
            this.editing = false;
        }
    },
    created() {
        this.employeeId = this.$route.params.employeeId;
        this.getEmployeeDetails(this.$route.params.employeeId);
        this.getSkills(this.$route.params.employeeId);
        this.getRole(this.$route.params.employeeId)
    }
}
</script>

<style>
.button {
    width: 115px;
    margin: auto;
}
</style>