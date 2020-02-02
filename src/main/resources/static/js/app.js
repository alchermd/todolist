var app = new Vue({
    el: '#app',
    data: {
        tasks: [],
        newTask: '',
    },
    created() {
        fetch('/tasks/')
            .then(response => response.json())
            .then(tasks => {
                this.tasks = tasks.sort(this.compareById);
            });
    },
    methods: {
        updateTask(event, task) {
            fetch(`/tasks/${task.id}/`, {
                method: 'put',
                body: JSON.stringify(task),
                headers: {
                    'Content-Type': 'application/json',
                }
            });
        },
        addTask(e) {
            e.preventDefault();

            fetch('/tasks/', {
                method: 'POST',
                body: JSON.stringify({
                    body: this.newTask
                }),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.status === 200) {
                        this.newTask = '';
                        alert('Task added!');
                    }

                    return response.json();
                })
                .then(task => this.tasks.push(task));
        },
        deleteTask(task) {
            fetch(`/tasks/${task.id}/`, {
                method: 'delete',
            })
                .then(response => {
                    let index = this.tasks.map(task => task.id).indexOf(task.id);
                    this.tasks.splice(index, 1);

                    alert('Task deleted');
                });
        },
        compareById(a, b) {
            if (a.id < b.id) {
                return -1;
            }

            return 1;
        },
    },
});