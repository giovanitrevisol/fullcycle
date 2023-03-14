resource "local_file" "exemplo" {
  filename = "exemplo.txt"
  content  = conteudo
}

variable "conteudo" {

}
