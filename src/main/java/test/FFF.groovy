package test
import com.thoughtworks.qdox.JavaProjectBuilder
import jdk.internal.util.xml.impl.ReaderUTF8

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.OpenOption
import java.nio.file.Paths

def builder=new JavaProjectBuilder();
builder.setEncoding(StandardCharsets.UTF_8.toString());
ReaderUTF8 readerUTF8=new ReaderUTF8(Files.newInputStream(Paths.get("D:\\IDEA Projects\\java8\\src\\test\\java\\test\\HHHTest.java"), LinkOption.NOFOLLOW_LINKS))
builder.addSource(readerUTF8);
def clazz = builder.getClasses().iterator().next();
def methodNums = clazz.getMethods();
print methodNums.size();
print ;







/*groovyScript("import com.thoughtworks.qdox.JavaProjectBuilder ;" +
        "import java.nio.charset.StandardCharsets;" +
        "def builder = new JavaProjectBuilder(); " +
        "builder.setEncoding(StandardCharsets.UTF_8.toString());" +
        "def virtualFile=_editor.getVirtualFile(); " +
        "def path=virtualFile.getFileSystem().refreshAndFindFileByPath(virtualFile.getPath()).getPath();" +
        "def file = new File(path); " +
        "builder.addSource(file);  " +
        "def clazz = builder.getClasses().iterator().next(); " +
        "def methodNums = clazz.getMethods();" +
        "builder=null; " +
        "return methodNums.size()+1;");*/

