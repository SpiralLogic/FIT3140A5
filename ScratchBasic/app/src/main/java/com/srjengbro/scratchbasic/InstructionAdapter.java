package com.srjengbro.scratchbasic;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.srjengbro.scratchbasic.instructions.GotoInstruction;
import com.srjengbro.scratchbasic.instructions.IfInstruction;
import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.RemInstruction;

import java.util.ArrayList;


public class InstructionAdapter extends BaseAdapter implements ListAdapter {

    /**
     *
     */
    private LayoutInflater inflater;
    /**
     *
     */
    private ArrayList<Instruction> instructionList;
    /**
     *
     */
    private Context context;


    /**
     * @param instructionList
     * @param ctx
     */
    public InstructionAdapter(ArrayList<Instruction> instructionList, Context ctx) {
        super();
        inflater = LayoutInflater.from(ctx);
        this.instructionList = instructionList;
        this.context = ctx;

    }

    /**
     * @return
     */
    @Override
    public int getCount() {
        return instructionList.size();
    }

    /**
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return instructionList.get(position);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Creates the layout from the instructionText line template.
     * There will be no remove button if it is the first line so that the first line cant be removed
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.instruction_line, parent, false);
            holder = new ViewHolder();
            holder.instructionText = (TextView) convertView.findViewById(R.id.instruction);
            holder.commandList = (Spinner) convertView.findViewById(R.id.spn_command);
            holder.editButton = (Button) convertView.findViewById(R.id.edit_button);
            holder.addButton = (Button) convertView.findViewById(R.id.add_button);
            holder.removeButton = (Button) convertView.findViewById(R.id.remove_button);
            holder.lineNumber = (TextView) convertView.findViewById(R.id.line_number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Instruction i = instructionList.get(position);
        if ((position == 0) && (instructionList.size() == 1)) {
            holder.removeButton.setVisibility(View.INVISIBLE);
        } else {
            holder.removeButton.setVisibility(View.VISIBLE);
        }

        holder.editButton.setTag(position);
        holder.addButton.setTag(position);
        holder.removeButton.setTag(position);
        holder.commandList.setTag(position);

        Integer cmdpos = ((ArrayAdapter<String>) holder.commandList.getAdapter()).getPosition(i.getName());
        holder.commandList.setSelection(cmdpos);

        holder.instructionText.setText(i.getInstruction());
        holder.commandList.setTag(position);
        convertView.setOnClickListener(onViewClick());
        holder.commandList.setOnItemSelectedListener(onCommandSelected());
        holder.editButton.setOnClickListener(onEditClick());
        holder.addButton.setOnClickListener(onAddClick());
        holder.removeButton.setOnClickListener(onRemoveClick());

        holder.lineNumber.setText(((Integer) position).toString());
        return convertView;
    }


    /**
     * @return
     */
    private View.OnClickListener onViewClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] position = new int[2];
                v.getLocationOnScreen(position);
                Toast.makeText(((View) v.getParent()).getContext(), ((Integer) position[1]).toString(), Toast.LENGTH_SHORT).show();

            }
        };
    }

    /**
     * @return
     */
    private AdapterView.OnItemSelectedListener onCommandSelected() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                Integer position = (Integer) parent.getTag();
                if (position < 0) {
                    return;
                }
                Spinner spinner = (Spinner) parent;
                Instruction i = instructionList.get(position);
                String instructionText = spinner.getSelectedItem().toString();
                if (!i.getName().equals(instructionText)) {
                    try {
                        i = InstructionMaker.generateInstruction(instructionText);
                        instructionList.set(position, i);
                        openInstructionDialog(i);
                    } catch (Exception e) {
                        Toast.makeText(((View) parent.getParent()).getContext(), "Not a valid instruction", Toast.LENGTH_SHORT).show();
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }


    /**
     * @return
     */
    private View.OnClickListener onEditClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = (Integer) v.getTag();
                Instruction inst = instructionList.get(position);
                openInstructionDialog(inst);
                notifyDataSetChanged();
            }
        };
    }

    /**
     * @param inst
     */
    private void openInstructionDialog(Instruction inst) {
        final InstructionAdapter adapter = this;
        InstructionDialog newFragment = new InstructionDialog();
        newFragment.setInstruction(inst);
        newFragment.setAdapter(adapter);
        newFragment.show(((FragmentActivity) context).getFragmentManager(), context.getString(R.string.instructiondialog_title));
    }

    /**
     * @return
     */
    private View.OnClickListener onAddClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = (Integer) v.getTag();
                Instruction newInstruction = new RemInstruction();
                instructionList.add(position + 1, newInstruction);
                updateGotoLinesAdd(position);
                notifyDataSetChanged();
            }
        };
    }

    /**
     * @return
     */
    private View.OnClickListener onRemoveClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                instructionList.remove(position);
                updateGotoLinesDelete(position);
                notifyDataSetChanged();
            }
        };
    }

    /**
     * @param fromPosition
     */
    private void updateGotoLinesDelete(int fromPosition) {
        Instruction inst;
        for (int i = 0; i < instructionList.size(); i++) {
            inst = instructionList.get(i);
            GotoInstruction gi;
            if (inst instanceof GotoInstruction) {
                gi = (GotoInstruction) inst;
                if (gi.getGotoLine() != null && gi.getGotoLine() < fromPosition) {
                    gi.decreaseGotoLine();
                }
            }
            if (inst instanceof IfInstruction) {
                IfInstruction ifi = (IfInstruction) inst;
                gi = ifi.getGotoInstruction();
                if (gi.getGotoLine() != null && gi.getGotoLine() > fromPosition) {
                    gi.decreaseGotoLine();
                    ifi.updateInstructionText();
                }
            }
        }
    }

    /**
     * @param toPosition
     */
    private void updateGotoLinesAdd(int toPosition) {
        Instruction inst;
        for (int i = 0; i < instructionList.size(); i++) {
            inst = instructionList.get(i);
            GotoInstruction gi;
            if (inst instanceof GotoInstruction) {
                gi = (GotoInstruction) inst;
                if (gi.getGotoLine() != null && gi.getGotoLine() > toPosition) {
                    ((GotoInstruction) inst).increaseGotoLine();
                }
            }
            if (inst instanceof IfInstruction) {
                IfInstruction ifi = (IfInstruction) inst;
                gi = ifi.getGotoInstruction();
                if (gi.getGotoLine() != null && gi.getGotoLine() > toPosition) {
                    gi.increaseGotoLine();
                    ifi.updateInstructionText();
                }
            }
        }
    }

    /**
     *
     */
    private static class ViewHolder {

        public TextView lineNumber;
        public TextView instructionText;
        public Spinner commandList;
        public Button editButton;
        public Button addButton;
        public Button removeButton;
    }
}
