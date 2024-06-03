var currentPrjId = null; // 현재 선택된 expId를 저장할 변수


            function openPrjEditModal() {
                if(currentPrjId) {
                    fetch(`http://localhost:8080/projects/findById?projectId=${currentPrjId}`)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('학력 정보를 불러오는 중에 문제가 발생했습니다.');
                        }
                        return response.json();
                    })
                    .then(project => {
                        // API 호출이 성공하면 모달에 정보를 채움
                        document.getElementById("projectId").value = project.project_id;
                        document.getElementById("projectName").value = project.name;
                        document.getElementById("description").value = project.description;
                        document.getElementById("startDate").value = project.start_date;
                        document.getElementById("endDate").value = project.end_date;
                        document.getElementById("role").value = project.role;
                        document.getElementById("skillList").value = project.skill_list;
                        document.getElementById("github").value = project.github;

                        // 모달 열기
                        $("#editProjectModal").modal("show");
                    })
                    .catch(error => {
                        // 에러 처리
                        console.error("에러:", error.message);
                        alert(error.message);
                    });
                 } else {
                    alert("학력 정보를 가져올 수 없습니다. 다시 시도해주세요.");
                }

            }
    /*
        function savePrjChanges() {
            // form의 name 필드 가져오기
            var form = document.getElementById('editProjectForm');
            var educationId = form.project_id.value;
            var school = form.projectName.value;
            var degree = form.description.value;
            var major = form.start_date.value;
            var content = form.end_date.value;
            var startDate = form.role.value;
            var endDate = form.skill_list.value;
            var img = form.img.value;


            // AJAX call to save changes (you need to implement the endpoint and method)
            fetch('/edu/update', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    eduId: educationId,
                    school: school,
                    graduate: degree,
                    major: major,
                    start_date: startDate,
                    end_date: endDate,
                    content: content
                }),
            })
            .then(response => response.json())
            .then(data => {
                // Handle success response
                console.log('Success:', data);
                location.reload(); // Reload the page to see the updated content
            })
            .catch((error) => {
                // Handle error response
                console.error('Error:', error);
            });
        }

        function showMessage() {
            console.log("Button clicked!");
        }
        */

        // 수정 및 삭제 선택을 위한 모달을 열 때 expId를 저장하는 함수
        function openEditOrDeleteprjModal(prjId) {
            console.log(prjId);
            currentPrjId = prjId;
            $("#editOrDeleteProjectModal").modal("show");
        }

        function openAddPrjModal() {
             $("#addProjectModal").modal("show");
        }



        function deleteProject() {
            if(currentPrjId) {
                    fetch(`http://localhost:8080/projects/delete?projectId=${currentPrjId}`, {
                        method: 'DELETE',
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('프로젝트 정보를 삭제하는 중에 문제가 발생했습니다.');
                        }
                        else {
                            alert("프로젝트 정보가 삭제되었습니다.");
                        }
                    })
                    .then(data => {
                        // Handle success response
                        console.log('Success:', data);
                        location.reload(); // Reload the page to see the updated content
                    })
                    .catch(error => {
                        // 에러 처리
                        console.error("에러:", error.message);
                        alert(error.message);
                    });
                 } else {
                    alert("프로젝트 정보를 삭제할 수 없습니다. 다시 시도해주세요.");
                }
        }