<template>
  <div id="main">
      <br><br>
      <h2>Update Role</h2>
      <br><br>
    <label>Role:</label>
    <select v-model="roleToEdit.roleId">
        <option value="1">Technical Consultant</option>
        <option value="2">Project Manager</option>
        <option value="3">Director</option>
        <option value="4">Chief</option>
    </select>
    <br>
    <label>Business Unit:</label>
    <select v-model="roleToEdit.businessUnitId">
        <option value="1">Digital Experience Group</option>
        <option value="2">Adobe</option>
        <option value="3">IBM NBU</option>
        <option value="4">API Management</option>
    </select><br><br>
    <button v-on:click="updateRole(employeeId)">Save</button>
    <button v-on:click="goBack(employeeId)">Cancel</button>
    <button v-on:click="deleteRole(employeeId)" class="delete">Delete Role</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            employeeId: '',
            role: {},
            roleToEdit: {
                employeeId: this.$route.params.employeeId,
                roleId: '',
                businessUnitId: ''
            }
        }
    },
    methods: {
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
                    this.roleToEdit.employeeBusinessUnitRoleId = role.employeeBusinessUnitRoleId;
                    this.roleToEdit.employeeId = role.employeeId;
                }
            )
            .catch (
                (err) => {
                    console.error(err)
                }
            )
        },
        updateRole(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/role`,
                {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.roleToEdit)
            })
            .then(
                window.alert('Role Updated')
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.goBack(employeeId);
        },
        deleteRole(employeeId) {
           fetch(`http://localhost:8080/Perficient/employees/${employeeId}/role`, 
                {
                    method: 'DELETE'
                })
            .then(
                window.alert('Role Deleted')
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.goBack(employeeId);
        },
        goBack(employeeId) {
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        }
    },
    created() {
        this.employeeId = this.$route.params.employeeId;
        this.getRole(this.$route.params.employeeId);
    }
}
</script>

<style>

</style>