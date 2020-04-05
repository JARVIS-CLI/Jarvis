package com.nlstn.jarvis.modules.command.commands.util;

import com.nlstn.jarvis.modules.command.Command;
import com.nlstn.jarvis.modules.command.CommandDomain;
import com.nlstn.jarvis.util.FileDownload;

public class FileDownloadCommand extends Command {

    public FileDownloadCommand() {
        super("FileDownloadCommand", CommandDomain.UTIL, new String[] { "download" });
    }

    @Override
    public void execute() {
        FileDownload.download(args[0], args[1]);
    }

    @Override
    public boolean validateArguments() {
        return args.length == 2;
    }

}