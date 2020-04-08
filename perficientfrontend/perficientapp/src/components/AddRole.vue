<template>
  <div id="main">
      <br><br>
        <h2>Role</h2><br>
            <label>Role:</label>
            <select v-model="role.roleId">
                <option value="1">Technical Consultant</option>
                <option value="2">Project Manager</option>
                <option value="3">Director</option>
                <option value="4">Chief</option>
            </select><br>
            <label>Business Unit:</label>
            <select v-model="role.businessUnitId">
                <option value="1">Digital Experience Group</option>
                <option value="2">Adobe</option>
                <option value="3">IBM NBU</option>
                <option value="4">API Management</option>
            </select>
            <br>
        <button v-on:click='setRoleEmployeeId'>Next</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            newEmployee: {},
            role: {
                employeeId: '',
                roleId: '',
                businessUnitId: ''
            }
        }
    },
    methods: {
        setRoleEmployeeId() {
            this.role.employeeId = this.newEmployee.employeeId;
            this.addRole(this.newEmployee.employeeId);
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
        addRole(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/role`,
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                },
                body: JSON.stringify(this.role)
            })
            .catch(
                (err) => {
                    console.error(err)
                }
           )
            this.$router.push({ name: 'add-field' });
        }
    },
    created() {
    this.setNewEmployee();
    }
}
</script>

<style>

</style>