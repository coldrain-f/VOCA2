document.addEventListener("DOMContentLoaded", () =>{
    const folderSelect = document.querySelector("#folderSelect")
    const categorySelect = document.querySelector("#categorySelect")

    const form = document.querySelector("#folderActionForm")
    folderSelect.addEventListener("change", () => {
        
        form.submit()
        
    })

    categorySelect.addEventListener("change", () => {

        form.submit()
    })

    

})