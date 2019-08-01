package retake.instagraph.domain.dtos.posts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostImportRootDto {

    @XmlElement(name = "post")
    private PostImportDto[] postImportDtos;

    public PostImportRootDto() {
    }

    public PostImportDto[] getPostImportDtos() {
        return postImportDtos;
    }

    public void setPostImportDtos(PostImportDto[] postImportDtos) {
        this.postImportDtos = postImportDtos;
    }
}
