import Vue from 'vue'
import Router from 'vue-router'
import Employees from './components/Employees.vue'
import AddEmployee from './components/AddEmployee.vue'
import EmployeeDetails from './components/EmployeeDetails.vue'
import UpdateSkill from './components/UpdateSkill.vue'
import UpdateAddress from './components/UpdateAddress.vue'
import UpdateRole from './components/UpdateRole.vue'
import AddAddress from './components/AddAddress.vue'
import AddRole from './components/AddRole.vue'
import AddField from './components/AddField.vue'
import AddSkill from './components/AddSkill.vue'
import AddExistingAddress from './components/AddExistingAddress.vue'
import AddExistingRole from './components/AddExistingRole.vue'
import AddExistingSkill from './components/AddExistingSkill.vue'
import AddExistingField from './components/AddExistingField.vue'
import AddAssignment from './components/AddAssignment.vue'
import UpdateAssignment from './components/UpdateAssignment.vue'
Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: Employees
        },
        {
            path: '/employees',
            name: 'employees',
            component: Employees
        },
        {
            path: '/add-employee',
            name: 'add-employee',
            component: AddEmployee
        },
        {
            path: '/employees/:employeeId',
            name: 'employee-details',
            component: EmployeeDetails
        },
        {
            path: '/employees/:employeeId/skills/:skillId/fields/:fieldId',
            name: 'update-skill',
            component: UpdateSkill
        },
        {
            path: '/employees/:employeeId/address',
            name: 'update-address',
            component: UpdateAddress
        },
        {
            path: '/employees/:employeeId/role',
            name: 'update-role',
            component: UpdateRole
        },
        {
            path: '/employees/:employeeId/assignment',
            name: 'update-assignment',
            component: UpdateAssignment
        },
        {
            path: '/add-address',
            name: 'add-address',
            component: AddAddress
        },
        {
            path: '/add-role',
            name: 'add-role',
            component: AddRole
        },
        {   
            path: '/add-field',
            name: 'add-field',
            component: AddField
        },
        {
            path: '/add-skill',
            name: 'add-skill',
            component: AddSkill
        },
        {
            path: '/add-assignment',
            name: 'add-assignment',
            component: AddAssignment
        },
        {
            path: '/employees/:employeeId/add-address',
            name: 'add-existing-address',
            component: AddExistingAddress
        },
        {
            path: '/employees/:employeeId/add-role',
            name: 'add-existing-role',
            component: AddExistingRole
        },
        {
            path: '/employees/:employeeId/add-skill',
            name: 'add-existing-skill',
            component: AddExistingSkill
        },
        {
            path: '/employees/:employeeId/add-field',
            name: 'add-existing-field',
            component: AddExistingField
        }
    ]
})