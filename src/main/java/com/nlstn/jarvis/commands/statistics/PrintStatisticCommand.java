package com.nlstn.jarvis.commands.statistics;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class PrintStatisticCommand extends Command {

    public PrintStatisticCommand() {
        super(CommandDomain.STATISTICS, new String[] { "print", "printStatistic", "printStat" });
    }

    @Override
    public void execute() {
        int value = ModuleHandler.getStatisticsModule().getStatisticsValue(args[0]);
        Logger.info("Statistic: " + args[0] + ", Value: " + value);
    }

    @Override
    public boolean validateArguments() {
        return args.length == 1;
    }

}