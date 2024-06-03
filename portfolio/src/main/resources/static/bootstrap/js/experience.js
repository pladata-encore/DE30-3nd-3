var currentExpId = null; // 현재 선택된 expId를 저장할 변수


            function openExpEditModal() {
                if(currentExpId) {
                    fetch(`http://localhost:8080/exp/get?expId=${currentExpId}`)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('경력 정보를 불러오는 중에 문제가 발생했습니다.');
                        }
                        return response.json();
                    })
                    .then(experience => {
                        // API 호출이 성공하면 모달에 정보를 채움
                        document.getElementById("experienceId").value = experience.expId;
                        document.getElementById("startDate").value = experience.startDate;
                        document.getElementById("endDate").value = experience.endDate;
                        document.getElementById("job").value = experience.job;
                        document.getElementById("companyName").value = experience.companyName;
                        document.getElementById("content").value = experience.content;

                        // 모달 열기
                        $("#editExperienceModal").modal("show");
                    })
                    .catch(error => {
                        // 에러 처리
                        console.error("에러:", error.message);
                        alert(error.message);
                    });
                 } else {
                    alert("경력 정보를 가져올 수 없습니다. 다시 시도해주세요.");
                }

            }
        function saveExpChanges() {
            var form = document.getElementById('editExperienceForm');
            var experienceId = form.experienceId.value;
            var startDate = form.startDate.value;
            var endDate = form.endDate.value;
            var job = form.job.value;
            var companyName = form.companyName.value;
            var content = form.content.value;

            // AJAX call to save changes (you need to implement the endpoint and method)
            fetch('/exp/update', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    expId: experienceId,
                    startDate: startDate,
                    endDate: endDate,
                    job: job,
                    companyName: companyName,
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

        // 수정 및 삭제 선택을 위한 모달을 열 때 expId를 저장하는 함수
        function openEditOrDeleteExpModal(expId) {
            currentExpId = expId;
            $("#editOrDeleteExpModal").modal("show");
        }

        function deleteExperience() {
            if(currentExpId) {
                    fetch(`http://localhost:8080/exp/delete?number=${currentExpId}`, {
                        method: 'DELETE',
                    })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('경력 정보를 삭제하는 중에 문제가 발생했습니다.');
                        }
                        else {
                            alert("경력 정보가 삭제되었습니다.");
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
                    alert("경력 정보를 삭제할 수 없습니다. 다시 시도해주세요.");
                }
        }